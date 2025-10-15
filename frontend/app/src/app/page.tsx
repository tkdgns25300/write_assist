"use client";

import { useEffect, useState } from 'react';
import CorrectionForm from '@/components/home/CorrectionForm';
import ResultDisplay from '@/components/home/ResultDisplay';
import SettingsPanel from '@/components/home/SettingsPanel';
import { getPresets, getUsage, postCorrectText } from '@/lib/api';
import { PresetResponse, TextCorrectionResponse, UsageResponse, Tone, Purpose, LengthType, StyleType } from '@/types/api';

export interface Settings {
  tone: Tone;
  purpose: Purpose;
  lengthType: LengthType;
  styleType: StyleType;
}

export default function Home() {
  const [inputText, setInputText] = useState('');
  const [settings, setSettings] = useState<Settings>({
    tone: 'STANDARD',
    purpose: 'INFORMATION',
    lengthType: 'STANDARD',
    styleType: 'CONCISE_CLEAR',
  });
  const [presets, setPresets] = useState<PresetResponse[]>([]);
  const [result, setResult] = useState<TextCorrectionResponse | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [usage, setUsage] = useState<UsageResponse | null>(null);
  const [error, setError] = useState<string | null>(null);


  useEffect(() => {
    // 페이지 로드 시 프리셋과 사용량 정보 가져오기
    const fetchInitialData = async () => {
      setIsLoading(true);
      try {
        const [presetsData, usageData] = await Promise.all([
          getPresets(),
          getUsage(),
        ]);
        setPresets(presetsData);
        setUsage(usageData);
      } catch (error) {
        console.error('Failed to fetch initial data:', error);
        setError('Failed to load initial data. Please try refreshing the page.');
      } finally {
        setIsLoading(false);
      }
    };

    fetchInitialData();
  }, []);

  const handleSettingsChange = (newSettings: Partial<Settings>) => {
    setSettings(prev => ({ ...prev, ...newSettings }));
  };

  const handlePresetClick = (preset: PresetResponse) => {
    setSettings({
      tone: preset.tone.value,
      purpose: preset.purpose.value,
      lengthType: preset.lengthType.value,
      styleType: preset.styleType.value,
    });
  };

  const handleGenerate = async () => {
    if (!inputText || isLoading) return;

    setIsLoading(true);
    setResult(null);
    setError(null);

    try {
      const response = await postCorrectText({
        text: inputText,
        ...settings,
      });
      setResult(response);
      if (usage) {
        setUsage(prev => ({ ...prev!, remainingUsage: response.remainingUsage, usedToday: prev!.usedToday + 1 }));
      }
    } catch (error: any) {
      console.error('Failed to correct text:', error);
      setError(error.message || 'An error occurred during text correction.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-8">
      {error && <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">{error}</div>}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {/* Left Column: Input and Results */}
        <div className="lg:col-span-2 space-y-8">
          <CorrectionForm
            inputText={inputText}
            setInputText={setInputText}
            isLoading={isLoading}
            onGenerate={handleGenerate}
            remainingUsage={usage?.remainingUsage}
          />
          {result && <ResultDisplay result={result} />}
        </div>

        {/* Right Column: Settings */}
        <div className="lg:col-span-1">
          <SettingsPanel
            settings={settings}
            onSettingsChange={handleSettingsChange}
            presets={presets}
            onPresetClick={handlePresetClick}
          />
        </div>
      </div>
    </div>
  );
}
