"use client";

import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import Slider from '@/components/ui/Slider';

export default function SettingsPanel() {
  // 임시 데이터. 실제 기능 구현 시 state로 대체됩니다.
  const toneValue = 3;
  const purpose = 'INFORMATION';
  const length = 'STANDARD';
  const style = 'CONCISE_CLEAR';

  return (
    <Card className="space-y-8">
      {/* Quick Presets */}
      <div className="space-y-4">
        <h2 className="text-lg font-semibold text-gray-800">Quick Presets</h2>
        <div className="space-y-2">
          <Button variant="secondary">Standard Business Mail</Button>
          <Button variant="secondary">Casual Colleague Chat</Button>
          <Button variant="secondary">Polite Request/Refusal</Button>
          <Button variant="secondary">Report Draft</Button>
        </div>
      </div>

      {/* Custom Settings */}
      <div className="space-y-6">
        <h2 className="text-lg font-semibold text-gray-800">Custom Settings</h2>

        {/* Tone */}
        <div className="space-y-3">
          <div className="flex justify-between items-center text-sm text-gray-600">
            <span>Tone: Level {toneValue}</span>
          </div>
          <Slider
            min={1}
            max={5}
            step={1}
            value={toneValue}
            onChange={() => {}}
          />
          <div className="flex justify-between items-center text-xs text-gray-500">
            <span>Very Casual</span>
            <span>Very Formal</span>
          </div>
        </div>

        {/* Purpose */}
        <div className="space-y-3">
          <label className="text-sm font-medium text-gray-600">Purpose</label>
          <div className="grid grid-cols-2 gap-2">
            <Button variant={purpose === 'INFORMATION' ? 'primary' : 'secondary'}>Information</Button>
            <Button variant={purpose === 'PERSUASION_REQUEST' ? 'primary' : 'secondary'}>Persuasion/Request</Button>
            <Button variant={purpose === 'APOLOGY_REFUSAL' ? 'primary' : 'secondary'}>Apology/Refusal</Button>
            <Button variant={purpose === 'THANKS_PRAISE' ? 'primary' : 'secondary'}>Thanks/Praise</Button>
          </div>
        </div>

        {/* Length */}
        <div className="space-y-3">
          <label className="text-sm font-medium text-gray-600">Length</label>
          <div className="grid grid-cols-3 gap-2">
            <Button variant={length === 'SHORT' ? 'primary' : 'secondary'}>Short</Button>
            <Button variant={length === 'STANDARD' ? 'primary' : 'secondary'}>Standard</Button>
            <Button variant={length === 'LONG' ? 'primary' : 'secondary'}>Long</Button>
          </div>
        </div>

        {/* Style */}
        <div className="space-y-3">
          <label className="text-sm font-medium text-gray-600">Style</label>
          <div className="flex flex-col space-y-2">
            <Button variant={style === 'CONCISE_CLEAR' ? 'primary' : 'secondary'}>Concise/Clear</Button>
            <Button variant={style === 'EMOTIONAL_NATURAL' ? 'primary' : 'secondary'}>Emotional/Natural</Button>
            <Button variant={style === 'PROFESSIONAL_ACADEMIC' ? 'primary' : 'secondary'}>Professional/Academic</Button>
          </div>
        </div>
      </div>
    </Card>
  );
}
