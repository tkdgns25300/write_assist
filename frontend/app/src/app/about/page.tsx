import HeroSection from '@/components/about/HeroSection';
import HowItWorksSection from '@/components/about/HowItWorksSection';
import WhyChooseUsSection from '@/components/about/WhyChooseUsSection';
import { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'About | Write Assist',
  description: 'Learn how Write Assist uses AI to solve writing challenges, helping everyone create context-perfect text quickly and easily.',
};

export default function AboutPage() {
  return (
    <div className="bg-white text-gray-800">
      <HeroSection />
      <HowItWorksSection />
      <WhyChooseUsSection />
    </div>
  );
}
