"use client";

import TextareaWithCounter from "@/components/ui/TextareaWithCounter";
import Button from "@/components/ui/Button";

interface CorrectionFormProps {
    inputText: string;
    setInputText: (value: string) => void;
    isLoading: boolean;
    onGenerate: () => void;
}

export default function CorrectionForm({
    inputText,
    setInputText,
    isLoading,
    onGenerate,
}: CorrectionFormProps) {
    return (
        <div className="space-y-4">
            <TextareaWithCounter
                value={inputText}
                onChange={(e) => setInputText(e.target.value)}
                maxLength={1000}
                placeholder="Please enter the text to be refined."
            />
            <div className="flex">
                <Button onClick={onGenerate} disabled={!inputText || isLoading} isLoading={isLoading} className="w-full py-4 text-lg">
                    Generate
                </Button>
            </div>
        </div>
    );
}
