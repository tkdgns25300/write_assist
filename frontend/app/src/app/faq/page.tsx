"use client";

import { useEffect, useState } from 'react';
import { getFaqs } from '@/lib/api';
import { FaqResponse } from '@/types/api';
import Accordion from '@/components/ui/Accordion';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import { Mail, HelpCircle, FileText, Bot, Settings, Languages } from 'lucide-react';

const iconMap: { [key: string]: React.ReactNode } = {
  "Is Write Assist really free to use?": <HelpCircle className="w-5 h-5 text-blue-600" />,
  "How secure is my data?": <FileText className="w-5 h-5 text-blue-600" />,
  "What types of text can I refine?": <Bot className="w-5 h-5 text-blue-600" />,
  "How accurate is the AI refinement?": <Settings className="w-5 h-5 text-blue-600" />,
  "Can I use Write Assist for commercial purposes?": <FileText className="w-5 h-5 text-blue-600" />,
  "What languages does Write Assist support?": <Languages className="w-5 h-5 text-blue-600" />,
  // Add other mappings here
};


export default function FaqPage() {
  const [faqs, setFaqs] = useState<FaqResponse[]>([]);
  const [openId, setOpenId] = useState<number | null>(null);

  useEffect(() => {
    const fetchFaqs = async () => {
      try {
        const data = await getFaqs();
        setFaqs(data);
        if (data.length > 0) {
          setOpenId(data[0].id); // Open the first FAQ by default
        }
      } catch (error) {
        console.error('Failed to fetch FAQs:', error);
      }
    };
    fetchFaqs();
  }, []);

  const handleToggle = (id: number) => {
    setOpenId(openId === id ? null : id);
  };

  return (
    <div className="bg-white py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto">
        {/* Header */}
        <div className="text-center mb-12">
          <h1 className="text-4xl font-bold text-gray-900">Frequently Asked Questions</h1>
          <p className="mt-4 text-lg text-gray-600">Everything you need to know about Write Assist</p>
        </div>

        {/* FAQ List */}
        <div className="space-y-4">
          {faqs.map((faq) => (
            <Card key={faq.id} className="!p-0">
              <Accordion
                isOpen={openId === faq.id}
                onToggle={() => handleToggle(faq.id)}
                title={
                  <div className="flex items-center space-x-3">
                    {iconMap[faq.question] || <HelpCircle className="w-5 h-5 text-blue-600" />}
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
        <Card className="mt-16 text-center bg-gray-50">
          <h2 className="text-2xl font-bold text-gray-900">Still have questions?</h2>
          <p className="mt-2 text-gray-600">Can't find the answer you're looking for? Feel free to reach out to our support team.</p>
          <div className="mt-6">
            <a href="mailto:tkdgns25300@gmail.com">
              <Button className="w-auto">
                <Mail className="w-4 h-4 mr-2" />
                Contact Support
              </Button>
            </a>
          </div>
        </Card>
      </div>
    </div>
  );
}
