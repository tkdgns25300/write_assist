"use client";

import Link from 'next/link';
import Button from '@/components/ui/Button';

export default function HeroSection() {
  return (
    <section className="text-center py-20">
      <div className="container max-w-5xl mx-auto px-4 sm:px-6 lg:px-8">
        <h1 className="text-4xl md:text-5xl font-semibold max-w-3xl mx-auto">
          <span className="block">Write Assist: Context Solved!</span>
          <span className="block">AI Instantly Generates Perfect Text</span>
        </h1>
        <p className="text-lg text-gray-600 mt-4">
          Context-Perfect Writing, No Login, 1 Second
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
