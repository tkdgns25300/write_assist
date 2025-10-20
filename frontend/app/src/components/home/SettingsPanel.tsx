"use client";

import Card from "@/components/ui/Card";
import Button from "@/components/ui/Button";
import Slider from "@/components/ui/Slider";
import { Settings } from "@/app/page";
import { PresetResponse, Tone } from "@/types/api";

interface SettingsPanelProps {
    settings: Settings;
    onSettingsChange: (newSettings: Partial<Settings>) => void;
    presets: PresetResponse[];
    onPresetClick: (preset: PresetResponse) => void;
}

export default function SettingsPanel({ settings, onSettingsChange, presets, onPresetClick }: SettingsPanelProps) {
    const toneValueMap: Record<Tone, number> = {
        VERY_CASUAL: 1,
        CASUAL: 2,
        STANDARD: 3,
        FORMAL: 4,
        VERY_FORMAL: 5,
    };
    const toneKeyMap: Record<number, Tone> = {
        1: "VERY_CASUAL",
        2: "CASUAL",
        3: "STANDARD",
        4: "FORMAL",
        5: "VERY_FORMAL",
    };

    return (
        <Card className="space-y-8">
            {/* Quick Presets */}
            <div className="space-y-4">
                <h2 className="text-lg font-medium text-gray-800">Quick Presets</h2>
                <div className="space-y-2">
                    {presets.map((preset) => (
                        <Button
                            key={preset.id}
                            variant="secondary"
                            onClick={() => onPresetClick(preset)}
                            className="w-full text-left"
                        >
                            {preset.name}
                        </Button>
                    ))}
                </div>
            </div>

            {/* Divider */}
            <div className="border-t border-gray-200"></div>

            {/* Custom Settings */}
            <div className="space-y-6">
                <h2 className="text-lg font-medium text-gray-800">Custom Settings</h2>

                {/* Tone */}
                <div className="space-y-3">
                    <div className="flex justify-between items-center text-sm text-gray-700">
                        <span>Tone: Level {toneValueMap[settings.tone]}</span>
                    </div>
                    <Slider
                        min={1}
                        max={5}
                        step={1}
                        value={toneValueMap[settings.tone]}
                        onChange={(e) => onSettingsChange({ tone: toneKeyMap[parseInt(e.target.value)] })}
                    />
                    <div className="flex justify-between items-center text-xs text-gray-500">
                        <span>Very Casual</span>
                        <span>Very Formal</span>
                    </div>
                </div>

                {/* Purpose */}
                <div className="space-y-4">
                    <label className="text-sm font-medium text-gray-700">Purpose</label>
                    <div className="grid grid-cols-2 gap-2">
                        <Button
                            onClick={() => onSettingsChange({ purpose: "INFORMATION" })}
                            variant={settings.purpose === "INFORMATION" ? "primary" : "secondary"}
                        >
                            Information
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ purpose: "PERSUASION_REQUEST" })}
                            variant={settings.purpose === "PERSUASION_REQUEST" ? "primary" : "secondary"}
                        >
                            Persuasion/Request
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ purpose: "APOLOGY_REFUSAL" })}
                            variant={settings.purpose === "APOLOGY_REFUSAL" ? "primary" : "secondary"}
                        >
                            Apology/Refusal
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ purpose: "THANKS_PRAISE" })}
                            variant={settings.purpose === "THANKS_PRAISE" ? "primary" : "secondary"}
                        >
                            Thanks/Praise
                        </Button>
                    </div>
                </div>

                {/* Length */}
                <div className="space-y-4">
                    <label className="text-sm font-medium text-gray-700">Length</label>
                    <div className="grid grid-cols-3 gap-2">
                        <Button
                            onClick={() => onSettingsChange({ lengthType: "SHORT" })}
                            variant={settings.lengthType === "SHORT" ? "primary" : "secondary"}
                        >
                            Short
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ lengthType: "STANDARD" })}
                            variant={settings.lengthType === "STANDARD" ? "primary" : "secondary"}
                        >
                            Standard
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ lengthType: "LONG" })}
                            variant={settings.lengthType === "LONG" ? "primary" : "secondary"}
                        >
                            Long
                        </Button>
                    </div>
                </div>

                {/* Style */}
                <div className="space-y-4">
                    <label className="text-sm font-medium text-gray-700">Style</label>
                    <div className="flex flex-col space-y-2">
                        <Button
                            onClick={() => onSettingsChange({ styleType: "CONCISE_CLEAR" })}
                            variant={settings.styleType === "CONCISE_CLEAR" ? "primary" : "secondary"}
                        >
                            Concise/Clear
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ styleType: "EMOTIONAL_NATURAL" })}
                            variant={settings.styleType === "EMOTIONAL_NATURAL" ? "primary" : "secondary"}
                        >
                            Emotional/Natural
                        </Button>
                        <Button
                            onClick={() => onSettingsChange({ styleType: "PROFESSIONAL_ACADEMIC" })}
                            variant={settings.styleType === "PROFESSIONAL_ACADEMIC" ? "primary" : "secondary"}
                        >
                            Professional/Academic
                        </Button>
                    </div>
                </div>
            </div>
        </Card>
    );
}
