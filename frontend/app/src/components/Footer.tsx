import Link from "next/link";
import { Github, Linkedin } from "lucide-react";

export default function Footer() {
    return (
        <footer className="bg-gray-200 text-gray-600">
            <div className="container mx-auto px-6 py-8">
                <div className="flex flex-col items-center justify-between sm:flex-row">
                    {/* Left Section: Logo and Copyright */}
                    <div className="flex flex-col items-center sm:items-start mb-6 sm:mb-0">
                        <Link href="/" className="flex items-center mb-2">
                            <span className="text-2xl font-bold text-gray-800">Write Assist</span>
                        </Link>
                        <p className="text-sm">© 2025 Write Assist. All rights reserved.</p>
                    </div>

                    {/* Right Section: Social Links */}
                    <div className="flex items-center space-x-4">
                        <a
                            href="https://github.com/tkdgns25300"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-gray-600 hover:text-gray-800 transition-colors"
                            aria-label="GitHub Profile"
                        >
                            <Github size={24} />
                        </a>
                        <a
                            href="https://www.linkedin.com/in/%EC%83%81%ED%9B%88-%EC%9D%B4-677669210/?locale=en_US"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-gray-600 hover:text-gray-800 transition-colors"
                            aria-label="LinkedIn Profile"
                        >
                            <Linkedin size={24} />
                        </a>
                    </div>
                </div>
            </div>
        </footer>
    );
}
