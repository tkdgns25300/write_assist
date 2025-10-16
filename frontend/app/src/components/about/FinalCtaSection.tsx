"use client";

import Link from 'next/link';
import Button from '@/components/ui/Button';

export default function FinalCtaSection() {
  return (
    <section className="py-20 bg-gray-50 px-4">
      <div className="container mx-auto text-center">
        <Link href="/">
          <Button className="w-auto px-8 py-3 text-lg">
            Start Context Refinement Now!
          </Button>
        </Link>
      </div>
    </section>
  );
}
