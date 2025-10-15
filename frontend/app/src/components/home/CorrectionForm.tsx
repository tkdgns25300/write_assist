"use client";

import TextareaWithCounter from '@/components/ui/TextareaWithCounter';
import Button from '@/components/ui/Button';

interface CorrectionFormProps {
  inputText: string;
  setInputText: (value: string) => void;
  isLoading: boolean;
  onGenerate: () => void;
  remainingUsage?: number;
}

export default function CorrectionForm({
  inputText,
  setInputText,
  isLoading,
  onGenerate,
  remainingUsage,
}: CorrectionFormProps) {
  return (
    <div className="space-y-4">
      <TextareaWithCounter
        value={inputText}
        onChange={(e) => setInputText(e.target.value)}
        maxLength={1000}
        placeholder="Please enter the text to be refined."
      />
      <div className="flex justify-between items-center">
        <span className="text-sm text-gray-600">
          {remainingUsage !== undefined ? `Remaining Corrections: ${remainingUsage}` : 'Loading usage...'}
        </span>
        <Button onClick={onGenerate} disabled={!inputText || isLoading} className="w-auto">
          {isLoading ? 'Generating...' : 'Generate'}
        </Button>
      </div>
    </div>
  );
}
