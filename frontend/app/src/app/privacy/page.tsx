import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Privacy Policy | Write Assist",
    description: "Read the official Privacy Policy for Write Assist. Understand how we collect, use, and protect your data when you use our AI text refinement service.",
    robots: "noindex, follow",
};

export default function PrivacyPage() {
    return (
        <div className="bg-white py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-3xl mx-auto">
                <div className="text-center mb-12">
                    <h1 className="text-4xl font-bold text-gray-900">Privacy Policy</h1>
                    <p className="mt-4 text-lg text-gray-600">How we collect, use, and protect your information</p>
                </div>

                <div className="space-y-6 text-gray-700">
                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">1. Information We Collect</h2>
                        <p>
                            Write Assist collects minimal information necessary to provide our services. We do not
                            require user registration or account creation.
                        </p>
                        <p>
                            When you use our service, we may collect the text content you input for refinement purposes.
                            This text is processed by our AI system and is not stored permanently.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">2. How We Use Your Information</h2>
                        <p>
                            The text you provide is used solely for the purpose of generating refined text based on your
                            selected settings (tone, purpose, length, style).
                        </p>
                        <p>
                            We do not share, sell, or distribute your input text to third parties. Your text is
                            processed in real-time and is not stored on our servers.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">3. Data Storage</h2>
                        <p>
                            Write Assist does not permanently store the text content you submit. Your text is processed
                            temporarily during the refinement process and is not retained after the service is
                            completed.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">4. Third-Party Services</h2>
                        <p>
                            Our service uses Google Gemini 2.5 Flash AI model for text processing. Your text is
                            transmitted to this service for processing, but is not stored by third-party services.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">5. Contact Us</h2>
                        <p>
                            If you have any questions about this Privacy Policy, please contact us at:{" "}
                            <a href="mailto:tkdgns25300@gmail.com" className="text-blue-600 hover:underline">
                                tkdgns25300@gmail.com
                            </a>
                        </p>
                    </section>

                    <section>
                        <p className="text-sm text-gray-500 mt-8">
                            Last updated:{" "}
                            {new Date().toLocaleDateString("en-US", { year: "numeric", month: "long", day: "numeric" })}
                        </p>
                    </section>
                </div>
            </div>
        </div>
    );
}
