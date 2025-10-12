import Link from 'next/link';

export default function Header() {
  return (
    <header className="bg-white shadow-md">
      <nav className="container mx-auto px-6 py-4 flex justify-between items-center">
        <div className="text-2xl font-bold text-gray-800">
          <Link href="/">Write Assist</Link>
        </div>
        <div className="flex space-x-4">
          <Link href="/" className="text-gray-600 hover:text-gray-800">Home</Link>
          <Link href="/about" className="text-gray-600 hover:text-gray-800">About</Link>
          <Link href="/faq" className="text-gray-600 hover:text-gray-800">FAQ</Link>
        </div>
      </nav>
    </header>
  );
}
