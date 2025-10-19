import Link from "next/link";
import { FaGithub, FaLinkedin } from "react-icons/fa";
import { FaXTwitter } from "react-icons/fa6";
import { Italianno } from "next/font/google";

const italianno = Italianno({
    weight: "400",
    subsets: ["latin"],
});

export default function Footer() {
    return (
        <footer className="bg-gray-50 text-gray-600 border-t border-gray-200">
            <div className="container mx-auto px-6 py-8">
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
