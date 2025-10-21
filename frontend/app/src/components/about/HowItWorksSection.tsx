"use client";

import { Edit3, Settings, Sparkles } from 'lucide-react';

const steps = [
  {
    icon: <Edit3 className="w-8 h-8 text-blue-600" />,
    title: '1. Input',
    description: 'Simply paste or type your text that needs refinement',
  },
  {
    icon: <Settings className="w-8 h-8 text-blue-600" />,
    title: '2. Set Context',
    description: 'Choose tone, purpose, length, and style to match your needs',
  },
  {
    icon: <Sparkles className="w-8 h-8 text-blue-600" />,
    title: '3. Generate & Copy',
    description: 'Get perfectly refined text in seconds, ready to use',
  },
];

export default function HowItWorksSection() {
  return (
    <section className="py-20 bg-white">
      <div className="container max-w-5xl mx-auto text-center px-4 sm:px-6 lg:px-8">
        <h2 className="text-4xl font-semibold mb-12">How It Works</h2>
        <div className="grid md:grid-cols-3 gap-12">
          {steps.map((step, index) => (
            <div key={index} className="flex flex-col items-center">
              <div className="bg-blue-100 rounded-full p-4 mb-4">
                {step.icon}
              </div>
              <h3 className="text-2xl font-semibold mb-2">{step.title}</h3>
              <p className="text-gray-600">{step.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
