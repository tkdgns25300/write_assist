"use client";

import { Zap, UserX, BrainCircuit, PenTool } from 'lucide-react';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import Link from 'next/link';

const features = [
  {
    icon: <Zap className="w-6 h-6 text-blue-600" />,
    title: 'Lightning Fast',
    description: 'Get refined text in just 1 second',
  },
  {
    icon: <UserX className="w-6 h-6 text-blue-600" />,
    title: 'No Registration',
    description: 'Start using immediately, no signup required',
  },
  {
    icon: <BrainCircuit className="w-6 h-6 text-blue-600" />,
    title: 'Context-Aware',
    description: 'AI understands your specific writing context',
  },
  {
    icon: <PenTool className="w-6 h-6 text-blue-600" />,
    title: 'Multiple Styles',
    description: 'From casual to professional, we’ve got you covered',
  },
];

export default function WhyChooseUsSection() {
  return (
    <section className="py-20 px-4">
      <div className="container mx-auto bg-gray-50 rounded-lg py-16">
        <h2 className="text-4xl font-bold mb-12 text-center">Why Choose Write Assist?</h2>
        <Card className="max-w-4xl mx-auto !p-12 bg-white">
          <div className="grid md:grid-cols-2 gap-x-12 gap-y-10 text-left">
            {features.map((feature, index) => (
              <div key={index} className="flex items-start space-x-4">
                <div className="flex-shrink-0">{feature.icon}</div>
                <div>
                  <h3 className="text-lg font-semibold">{feature.title}</h3>
                  <p className="text-gray-600 mt-1">{feature.description}</p>
                </div>
              </div>
            ))}
          </div>
        </Card>
        <div className="mt-12 text-center">
          <Link href="/">
            <Button className="px-8 py-3 text-lg">
              Start Context Refinement Now!
            </Button>
          </Link>
        </div>
      </div>
    </section>
  );
}
