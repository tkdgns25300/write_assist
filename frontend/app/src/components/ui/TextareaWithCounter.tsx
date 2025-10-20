"use client";

import React from "react";

interface TextareaWithCounterProps {
    value: string;
    onChange: (e: React.ChangeEvent<HTMLTextAreaElement>) => void;
    maxLength: number;
    placeholder?: string;
}

export default function TextareaWithCounter({ value, onChange, maxLength, placeholder }: TextareaWithCounterProps) {
    const currentLength = value.length;

    return (
        <div className="relative w-full">
            <textarea
                value={value}
                onChange={onChange}
                maxLength={maxLength}
                placeholder={placeholder}
                className="w-full h-48 p-4 border border-gray-200 rounded-md resize-none focus:outline-none focus:ring-2 focus:ring-blue-500 placeholder:text-gray-400"
            />
            <div className="absolute bottom-3 right-3 text-sm text-gray-400">
                {currentLength} / {maxLength} characters
            </div>
        </div>
    );
}
