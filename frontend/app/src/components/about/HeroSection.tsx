import Link from 'next/link';
import Button from '@/components/ui/Button';

export default function HeroSection() {
  return (
    <section className="text-center py-20">
      <div className="container max-w-5xl mx-auto px-4 sm:px-6 lg:px-8">
        <h1 className="text-4xl md:text-5xl font-semibold max-w-3xl mx-auto">
          Your AI Partner for Perfect Writing
        </h1>
        <p className="text-lg text-gray-600 mt-4 max-w-3xl mx-auto">
          Instantly refine your text for any situation. Adjust the tone, define the purpose, and choose the right style to create clear, confident, and compelling messages. Get started now and experience the future of writing.
        </p>
        <div className="mt-8 flex justify-center">
          <Link href="/">
            <Button className="w-auto px-8 py-3 text-lg">
              Start Context Refinement Now!
            </Button>
          </Link>
        </div>
      </div>
    </section>
  );
}
