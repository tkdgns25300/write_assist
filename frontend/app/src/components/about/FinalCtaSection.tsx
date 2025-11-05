import Link from 'next/link';
import Button from '@/components/ui/Button';

export default function FinalCtaSection() {
  return (
    <section className="bg-blue-600">
      <div className="container mx-auto max-w-5xl px-4 py-16 text-center sm:px-6 lg:px-8">
        <h2 className="text-3xl font-extrabold tracking-tight text-white sm:text-4xl">
          Ready to Transform Your Writing?
        </h2>
        <p className="mt-4 text-lg text-blue-100">
          Experience the power of context-aware AI. Get started for free, no registration required.
        </p>
        <div className="mt-8 flex justify-center">
          <Link href="/">
            <Button
              variant="secondary"
              className="w-auto px-8 py-3 text-lg font-semibold !text-blue-600 !border-blue-600 shadow-md transition hover:!bg-blue-600 hover:!text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
            >
              Start Context Refinement Now!
            </Button>
          </Link>
        </div>
      </div>
    </section>
  );
}
