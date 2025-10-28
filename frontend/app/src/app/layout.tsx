import type { Metadata } from "next";
import "./globals.css";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export const metadata: Metadata = {
    title: "Write Assist",
    description: "AI-Powered Context Refinement Service",
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="ko">
            <head>
                <script
                    async
                    src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-2290142008040402"
                    crossOrigin="anonymous"
                ></script>
            </head>
            <body className="flex flex-col min-h-screen bg-gray-50">
                <Header />
                <main className="flex-grow bg-white">{children}</main>
                <Footer />
            </body>
        </html>
    );
}
