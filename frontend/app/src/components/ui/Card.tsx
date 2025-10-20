"use client";

import React from "react";

interface CardProps {
    children: React.ReactNode;
    className?: string;
}

export default function Card({ children, className = "" }: CardProps) {
    return <div className={`bg-gray-50 rounded-lg p-6 ${className}`}>{children}</div>;
}
