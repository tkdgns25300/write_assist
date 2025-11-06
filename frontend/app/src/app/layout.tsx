import type { Metadata } from "next";
import "./globals.css";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Script from "next/script";

export const metadata: Metadata = {
    title: "Write Assist",
    description: "AI-Powered Context Refinement Service",
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    const gaId = process.env.NEXT_PUBLIC_GA_ID;
    const adSenseClientId = process.env.NEXT_PUBLIC_ADSENSE_CLIENT_ID;

    return (
        <html lang="ko">
            <head>
                {/* Google AdSense Script (Production Only) */}
                {process.env.NODE_ENV === "production" && adSenseClientId && (
                    <script
                        async
                        src={`https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=${adSenseClientId}`}
                        crossOrigin="anonymous"
                    ></script>
                )}
            </head>
            <body>
                {/* Google Analytics Scripts (Production Only) */}
                {process.env.NODE_ENV === "production" && gaId && (
                    <>
                        <Script
                            strategy="afterInteractive"
                            src={`https://www.googletagmanager.com/gtag/js?id=${gaId}`}
                        />
                        <Script id="google-analytics" strategy="afterInteractive">
                            {`
                                window.dataLayer = window.dataLayer || [];
                                function gtag(){dataLayer.push(arguments);}
                                gtag('js', new Date());
                                gtag('config', '${gaId}');
                            `}
                        </Script>
                    </>
                )}

                <div className="flex flex-col min-h-screen bg-gray-50">
                    <Header />
                    <main className="flex-grow bg-white">{children}</main>
                    <Footer />
                </div>
            </body>
        </html>
    );
}
