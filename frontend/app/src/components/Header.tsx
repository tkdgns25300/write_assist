import Link from "next/link";
import { Italianno } from "next/font/google";

const italianno = Italianno({
    weight: "400",
    subsets: ["latin"],
});

export default function Header() {
    return (
        <header className="bg-white shadow-sm sticky top-0 z-50">
            <nav className="container mx-auto px-6 py-3 flex justify-between items-center">
                {/* Logo */}
                <Link href="/" className="flex items-center">
                    <span className={`text-4xl font-bold text-gray-800 ${italianno.className}`}>Write Assist</span>
                </Link>

                {/* Navigation Links */}
                <div className="hidden md:flex items-center space-x-12">
                    <Link href="/about" className="text-gray-600 hover:text-gray-800 font-medium">
                        About
                    </Link>
                    <Link href="/faq" className="text-gray-600 hover:text-gray-800 font-medium">
                        FAQ
                    </Link>
                </div>
            </nav>
        </header>
    );
}
