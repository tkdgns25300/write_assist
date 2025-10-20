"use client";

import React from "react";

interface SliderProps {
    min: number;
    max: number;
    step: number;
    value: number;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function Slider({ min, max, step, value, onChange }: SliderProps) {
    return (
        <input
            type="range"
            min={min}
            max={max}
            step={step}
            value={value}
            onChange={onChange}
            className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer slider-blue"
            style={{
                background: `linear-gradient(to right, #e5e7eb 0%, #e5e7eb ${
                    ((value - min) / (max - min)) * 100
                }%, #d1d5db ${((value - min) / (max - min)) * 100}%, #d1d5db 100%)`,
            }}
        />
    );
}
