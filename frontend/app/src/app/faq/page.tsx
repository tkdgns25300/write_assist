"use client";

import { useEffect, useState } from 'react';
import { getFaqs } from '@/lib/api';
import { FaqResponse } from '@/types/api';
import Accordion from '@/components/ui/Accordion';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import { HelpCircle } from 'lucide-react';

export default function FaqPage() {
  const [faqs, setFaqs] = useState<FaqResponse[]>([]);
  const [openIds, setOpenIds] = useState<number[]>([]);

  useEffect(() => {
    const fetchFaqs = async () => {
      try {
        const data = await getFaqs();
        setFaqs(data);
      } catch (error) {
        console.error('Failed to fetch FAQs:', error);
      }
    };
    fetchFaqs();
  }, []);

  const handleToggle = (id: number) => {
    setOpenIds(prevOpenIds =>
      prevOpenIds.includes(id)
        ? prevOpenIds.filter(prevId => prevId !== id)
        : [...prevOpenIds, id]
    );
  };

  return (
    <div className="bg-white py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto">
        {/* Header */}
        <div className="text-center mb-12">
          <h1 className="text-4xl font-semibold text-gray-900">Frequently Asked Questions</h1>
          <p className="mt-4 text-lg text-gray-600">Everything you need to know about Write Assist</p>
        </div>

        {/* FAQ List */}
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

        {/* Contact Card */}
        <Card className="mt-16 text-center bg-blue-50 p-8">
          <h2 className="text-2xl font-semibold text-gray-900">Still have questions?</h2>
          <p className="mt-2 text-gray-600">Can't find the answer you're looking for? Feel free to reach out to our support team.</p>
          <div className="mt-6 flex justify-center">
            <a href="mailto:tkdgns25300@gmail.com">
              <Button className="w-auto">
                Contact Support
              </Button>
            </a>
          </div>
        </Card>
      </div>
    </div>
  );
}
