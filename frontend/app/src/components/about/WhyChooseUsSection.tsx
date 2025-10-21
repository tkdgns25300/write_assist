import { Zap, UserX, BrainCircuit, PenTool } from 'lucide-react';
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
    <section className="py-20">
      <div className="container max-w-5xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-gray-50 rounded-lg py-16">
          <h2 className="text-4xl font-semibold mb-12 text-center">Why Choose Write Assist?</h2>
          <div className="max-w-4xl mx-auto p-12">
            <div className="grid md:grid-cols-2 gap-x-12 gap-y-10 text-left">
              {features.map((feature, index) => (
                <div key={index} className="flex items-start space-x-4">
                  <div className="flex-shrink-0">{feature.icon}</div>
                  <div>
                    <h3 className="text-lg font-medium">{feature.title}</h3>
                    <p className="text-gray-600 mt-1">{feature.description}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
          <div className="mt-12 flex justify-center">
            <Link href="/">
              <Button className="px-8 py-3 text-lg">
                Start Context Refinement Now!
              </Button>
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
}
