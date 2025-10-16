"use client";

import Link from 'next/link';
import Button from '@/components/ui/Button';

export default function HeroSection() {
  return (
    <section className="text-center py-20 px-4">
      <h1 className="text-4xl md:text-5xl font-bold max-w-2xl mx-auto">
        Write Assist: Context Solved! AI Instantly Generates Perfect Text
      </h1>
      <p className="text-lg text-gray-600 mt-4">
        Context-Perfect Writing, No Login, 1 Second
      </p>
      <div className="mt-8">
        <Link href="/">
          <Button className="w-auto px-8 py-3 text-lg">
            Start Context Refinement Now!
          </Button>
        </Link>
      </div>
    </section>
  );
}
