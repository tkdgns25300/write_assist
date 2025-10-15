"use client";

import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';

const mockResults = [
  {
    title: 'Option 1',
    text: 'Refined version of your text with information purpose, standard length, and concise/clear style. Tone level: 3/5. This version maintains a balanced tone while ensuring clarity and professionalism in your communication.',
  },
  {
    title: 'Option 2',
    text: 'Refined version of your text with information purpose, standard length, and concise/clear style. Tone level: 5/5. This refined version adopts a more formal approach, suitable for professional correspondence and official documentation.',
  },
  {
    title: 'Option 3',
    text: 'Refined version of your text with information purpose, standard length, and concise/clear style. Tone level: 3/5. A streamlined version that delivers your message with maximum impact and minimal words.',
  },
];

export default function ResultDisplay() {
  return (
    <div className="space-y-6">
      <h2 className="text-xl font-semibold text-gray-800">Generated Results</h2>
      <div className="space-y-4">
        {mockResults.map((result, index) => (
          <Card key={index}>
            <div className="flex flex-col h-full">
              <h3 className="text-md font-semibold text-blue-600 mb-2">{result.title}</h3>
              <p className="text-gray-700 flex-grow">{result.text}</p>
              <div className="mt-4">
                <Button variant="primary">Copy Text</Button>
              </div>
            </div>
          </Card>
        ))}
      </div>
    </div>
  );
}
