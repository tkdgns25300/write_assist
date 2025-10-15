"use client";

import TextareaWithCounter from '@/components/ui/TextareaWithCounter';
import Button from '@/components/ui/Button';

export default function CorrectionForm() {
  return (
    <div className="space-y-4">
      <TextareaWithCounter
        value=""
        onChange={() => {}}
        maxLength={1000}
        placeholder="Please enter the text to be refined."
      />
      <Button disabled>
        Generate
      </Button>
    </div>
  );
}
