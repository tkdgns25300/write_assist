"use client";

import { useState } from 'react';
import { FaqResponse } from '@/types/api';
import Accordion from '@/components/ui/Accordion';
import Card from '@/components/ui/Card';
import { HelpCircle } from 'lucide-react';

interface FaqListProps {
  faqs: FaqResponse[];
}

export default function FaqList({ faqs }: FaqListProps) {
  const [openIds, setOpenIds] = useState<number[]>([]);

  const handleToggle = (id: number) => {
    setOpenIds(prevOpenIds =>
      prevOpenIds.includes(id)
        ? prevOpenIds.filter(prevId => prevId !== id)
        : [...prevOpenIds, id]
    );
  };

  return (
    <div className="space-y-4">
      {faqs.map((faq) => (
        <Card key={faq.id} className="!p-0">
          <Accordion
            isOpen={openIds.includes(faq.id)}
            onToggle={() => handleToggle(faq.id)}
            title={
              <div className="flex items-center space-x-3">
                <HelpCircle className="w-5 h-5 text-blue-600" />
                <span>{faq.question}</span>
              </div>
            }
          >
            {faq.answer}
          </Accordion>
        </Card>
      ))}
    </div>
  );
}
