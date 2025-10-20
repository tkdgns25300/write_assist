"use client";

import React from "react";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
    variant?: "primary" | "secondary";
}

export default function Button({ children, className = "", variant = "primary", ...props }: ButtonProps) {
    const baseStyles =
        "text-center rounded-md px-4 py-2 font-normal text-sm transition-colors focus:outline-none focus:ring-2 focus:ring-offset-2 min-h-[2.5rem] flex items-center justify-center";

    const variantStyles = {
        primary:
            "bg-blue-600 text-white hover:bg-blue-700 focus:ring-blue-500 disabled:bg-gray-300 disabled:cursor-not-allowed border border-blue-600",
        secondary:
            "bg-white text-gray-700 border border-gray-300 hover:bg-gray-50 hover:border-blue-500 focus:ring-blue-500",
    };

    return (
        <button className={`${baseStyles} ${variantStyles[variant]} ${className}`} {...props}>
            {children}
        </button>
    );
}
