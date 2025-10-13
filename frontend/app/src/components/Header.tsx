import Link from 'next/link';

export default function Header() {
  return (
    <header className="bg-white shadow-sm sticky top-0 z-50">
      <nav className="container mx-auto px-6 py-3 flex justify-between items-center">
        {/* Logo */}
        <Link href="/" className="flex items-center">
          <span className="text-2xl font-bold text-gray-800">
            Write Assist
          </span>
        </Link>

        {/* Navigation Links */}
        <div className="hidden md:flex items-center space-x-6">
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
