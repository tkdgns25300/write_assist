"use client";

import { useEffect, useState } from "react";
import CorrectionForm from "@/components/home/CorrectionForm";
import ResultDisplay from "@/components/home/ResultDisplay";
import SettingsPanel from "@/components/home/SettingsPanel";
import { postCorrectText } from "@/lib/api";
import {
    PresetResponse,
    TextCorrectionResponse,
    UsageResponse,
    Tone,
    Purpose,
    LengthType,
    StyleType,
} from "@/types/api";
// import FeatureSection from "@/components/home/FeatureSection"; // This will be removed.

export interface Settings {
    tone: Tone;
    purpose: Purpose;
    lengthType: LengthType;
    styleType: StyleType;
}

interface HomeClientProps {
    initialPresets: PresetResponse[];
    initialUsage: UsageResponse | null;
}

export default function HomeClient({ initialPresets, initialUsage }: HomeClientProps) {
    const [inputText, setInputText] = useState("");
    const [settings, setSettings] = useState<Settings>({
        tone: "STANDARD",
        purpose: "INFORMATION",
        lengthType: "STANDARD",
        styleType: "CONCISE_CLEAR",
    });
    const [presets] = useState<PresetResponse[]>(initialPresets);
    const [result, setResult] = useState<TextCorrectionResponse | null>(null);
    const [isLoading, setIsLoading] = useState(false);
    const [usage, setUsage] = useState<UsageResponse | null>(initialUsage);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const handleUsageUpdate = (event: Event) => {
            const customEvent = event as CustomEvent<UsageResponse>;
            const newUsage = customEvent.detail;
            if (newUsage) {
                setUsage(newUsage);
            }
        };
        window.addEventListener('usageUpdated', handleUsageUpdate);
        return () => {
            window.removeEventListener('usageUpdated', handleUsageUpdate);
        };
    }, []);

    const handleSettingsChange = (newSettings: Partial<Settings>) => {
        setSettings((prev) => ({ ...prev, ...newSettings }));
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
                const newUsage = {
                    ...usage,
                    remainingUsage: response.remainingUsage,
                    usedToday: usage.usedToday + 1,
                };
                setUsage(newUsage);
                window.dispatchEvent(new CustomEvent('usageUpdated', { detail: newUsage }));
            }
        } catch (error: unknown) {
            console.error("Failed to correct text:", error);
            if (error instanceof Error) {
                setError(error.message);
            } else {
                setError("An unknown error occurred during text correction.");
            }
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-8">
            {error && (
                <div
                    className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4"
                    role="alert"
                >
                    {error}
                </div>
            )}
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                <div className="lg:col-span-2 space-y-8">
                    <CorrectionForm
                        inputText={inputText}
                        setInputText={setInputText}
                        isLoading={isLoading}
                        onGenerate={handleGenerate}
                    />
                    {result && <ResultDisplay result={result} />}
                </div>
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
