"use client";

import Link from "next/link";
import { useEffect, useState } from "react";
import { FaGithub, FaLinkedin } from "react-icons/fa";
import { FaXTwitter } from "react-icons/fa6";
import { Italianno } from "next/font/google";
import { Gift } from "lucide-react";
import { getUsage } from "@/lib/api";
import { UsageResponse } from "@/types/api";

const italianno = Italianno({
    weight: "400",
    subsets: ["latin"],
});

export default function Footer() {
    const [usage, setUsage] = useState<UsageResponse | null>(null);

    const fetchUsage = async () => {
        try {
            const usageData = await getUsage();
            console.log("[Footer] Received from getUsage:", usageData); // For debugging
            setUsage(usageData);
        } catch (error) {
            console.error("Failed to fetch usage data:", error);
        }
    };

    useEffect(() => {
        fetchUsage(); // Initial fetch

        const handleUsageUpdate = (event: Event) => {
            const customEvent = event as CustomEvent<UsageResponse>;
            const newUsage = customEvent.detail;
            if (newUsage) {
                setUsage(newUsage);
            }
        };
        
        window.addEventListener('usageUpdated', handleUsageUpdate);

        return () => {
            window.removeEventListener('usageUpdated', handleUsageUpdate);
        };
    }, []);

    return (
        <footer className="bg-gray-50 text-gray-600 border-t border-gray-200">
            <div className="container mx-auto px-6 py-8">
                {/* Usage Info - Top */}
                {usage && (
                    <div className="flex items-center mb-10">
                        <Gift className="text-blue-600 mr-2" size={20} />
                        <span className="text-blue-600 text-sm font-medium">
                            Daily Free Refinements Remaining: {usage.remainingUsage} / {usage.dailyLimit}
                        </span>
                    </div>
                )}

                <div className="flex flex-col items-center justify-between sm:flex-row">
                    {/* Left Section: Logo and Copyright */}
                    <div className="flex flex-col items-center sm:items-start mb-6 sm:mb-0">
                        <Link href="/" className="flex items-center mb-2">
                            <span className={`text-4xl text-gray-800 ${italianno.className}`}>Write Assist</span>
                        </Link>
                        <p className="text-sm">© {new Date().getFullYear()} Write Assist. All rights reserved.</p>
                    </div>

                    {/* Right Section: Social Links */}
                    <div className="flex items-center space-x-6">
                        <a
                            href="https://github.com/tkdgns25300"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-gray-600 hover:text-blue-600 transition-colors"
                            aria-label="GitHub Profile"
                        >
                            <FaGithub size={24} />
                        </a>
                        <a
                            href="https://www.linkedin.com/in/%EC%83%81%ED%HNB8%EC%9D%B4-677669210/?locale=en_US"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-gray-600 hover:text-blue-600 transition-colors"
                            aria-label="LinkedIn Profile"
                        >
                            <FaLinkedin size={24} />
                        </a>
                        <a
                            href="https://x.com/tkdgns25300"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-gray-600 hover:text-blue-600 transition-colors"
                            aria-label="X (Twitter) Profile"
                        >
                            <FaXTwitter size={24} />
                        </a>
                    </div>
                </div>
            </div>
        </footer>
    );
}
