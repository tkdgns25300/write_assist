import HeroSection from '@/components/about/HeroSection';
import FeatureSection from '@/components/about/FeatureSection';
import FinalCtaSection from '@/components/about/FinalCtaSection'; // Import the new component
import { Metadata } from 'next';
import Link from 'next/link';
import Button from '@/components/ui/Button';

export const metadata: Metadata = {
  title: 'About | Write Assist',
  description: 'Learn how Write Assist uses AI to solve writing challenges, helping everyone create context-perfect text quickly and easily.',
};

export default function AboutPage() {
  return (
    <div className="bg-white text-gray-800">
      <HeroSection />
      <FeatureSection />
      <FinalCtaSection />
    </div>
  );
}
