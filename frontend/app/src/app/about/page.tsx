import HeroSection from '@/components/about/HeroSection';
import HowItWorksSection from '@/components/about/HowItWorksSection';
import WhyChooseUsSection from '@/components/about/WhyChooseUsSection';

export default function AboutPage() {
  return (
    <div className="bg-white text-gray-800">
      <HeroSection />
      <HowItWorksSection />
      <WhyChooseUsSection />
    </div>
  );
}
