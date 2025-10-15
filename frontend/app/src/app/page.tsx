"use client";

import { useEffect, useState } from 'react';
import CorrectionForm from '@/components/home/CorrectionForm';
import ResultDisplay from '@/components/home/ResultDisplay';
import SettingsPanel from '@/components/home/SettingsPanel';
import { getPresets, getUsage } from '@/lib/api';
import { PresetResponse, TextCorrectionResponse, UsageResponse } from '@/types/api';

export interface Settings {
  tone: 'VERY_CASUAL' | 'CASUAL' | 'STANDARD' | 'FORMAL' | 'VERY_FORMAL';
  purpose: 'INFORMATION' | 'PERSUASION_REQUEST' | 'APOLOGY_REFUSAL' | 'THANKS_PRAISE' | 'FREEFORM';
  lengthType: 'SHORT' | 'STANDARD' | 'LONG';
  styleType: 'CONCISE_CLEAR' | 'EMOTIONAL_NATURAL' | 'PROFESSIONAL_ACADEMIC';
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

  useEffect(() => {
    // 페이지 로드 시 프리셋과 사용량 정보 가져오기
    const fetchInitialData = async () => {
      try {
        const [presetsData, usageData] = await Promise.all([
          getPresets(),
          getUsage(),
        ]);
        setPresets(presetsData);
        setUsage(usageData);
      } catch (error) {
        console.error('Failed to fetch initial data:', error);
        // TODO: 사용자에게 에러 메시지 표시
      }
    };

    fetchInitialData();
  }, []);

  return (
    <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {/* Left Column: Input and Results */}
        <div className="lg:col-span-2 space-y-8">
          <CorrectionForm />
          {result && <ResultDisplay />}
        </div>

        {/* Right Column: Settings */}
        <div className="lg:col-span-1">
          <SettingsPanel />
        </div>
      </div>
    </div>
  );
}
