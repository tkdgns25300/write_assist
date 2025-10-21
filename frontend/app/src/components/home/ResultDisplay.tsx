"use client";

import { useState } from 'react';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import { TextCorrectionResponse } from '@/types/api';

interface ResultDisplayProps {
  result: TextCorrectionResponse;
}

export default function ResultDisplay({ result }: ResultDisplayProps) {
  const [copiedIndex, setCopiedIndex] = useState<number | null>(null);

  const handleCopy = (text: string, index: number) => {
    navigator.clipboard.writeText(text);
    setCopiedIndex(index);
    setTimeout(() => setCopiedIndex(null), 2000); // Hide message after 2 seconds
  };

  return (
    <div className="space-y-6">
      <h2 className="text-xl font-semibold text-gray-800">Generated Results</h2>
      <div className="space-y-4">
        {result.correctedTexts.map((text, index) => (
          <Card key={index}>
            <div className="flex flex-col h-full">
              <h3 className="text-md font-semibold text-blue-600 mb-2">{`Option ${index + 1}`}</h3>
              <p className="text-gray-700 flex-grow whitespace-pre-wrap">{text}</p>
              <div className="mt-4">
                <Button onClick={() => handleCopy(text, index)}>
                  {copiedIndex === index ? 'Copied!' : 'Copy Text'}
                </Button>
              </div>
            </div>
          </Card>
        ))}
      </div>
    </div>
  );
}
