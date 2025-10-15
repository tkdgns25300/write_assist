"use client";

import CorrectionForm from '@/components/home/CorrectionForm';
import ResultDisplay from '@/components/home/ResultDisplay';
import SettingsPanel from '@/components/home/SettingsPanel';

export default function Home() {
  const showResults = true; // 임시로 결과를 항상 표시하도록 설정

  return (
    <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {/* Left Column: Input and Results */}
        <div className="lg:col-span-2 space-y-8">
          <CorrectionForm />
          {showResults && <ResultDisplay />}
        </div>

        {/* Right Column: Settings */}
        <div className="lg:col-span-1">
          <SettingsPanel />
        </div>
      </div>
    </div>
  );
}
