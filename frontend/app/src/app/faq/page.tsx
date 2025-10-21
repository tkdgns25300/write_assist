import { getFaqs } from '@/lib/api';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import FaqList from '@/components/faq/FaqList';
import { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'Frequently Asked Questions | Write Assist',
  description: 'Find answers to common questions about Write Assist, including how to use the service, features, and free usage limits.',
};

export default async function FaqPage() {
  const faqs = await getFaqs();

  return (
    <div className="bg-white py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto">
        {/* Header */}
        <div className="text-center mb-12">
          <h1 className="text-4xl font-semibold text-gray-900">Frequently Asked Questions</h1>
          <p className="mt-4 text-lg text-gray-600">Everything you need to know about Write Assist</p>
        </div>

        {/* FAQ List */}
        <FaqList faqs={faqs} />

        {/* Contact Card */}
        <Card className="mt-16 text-center bg-blue-50 p-8">
          <h2 className="text-2xl font-semibold text-gray-900">Still have questions?</h2>
          <p className="mt-2 text-gray-600">Can't find the answer you're looking for? Feel free to reach out to our support team.</p>
          <div className="mt-6 flex justify-center">
            <a href="mailto:tkdgns25300@gmail.com">
              <Button className="w-auto">
                Contact Support
              </Button>
            </a>
          </div>
        </Card>
      </div>
    </div>
  );
}
