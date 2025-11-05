import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Terms of Service | Write Assist",
    description: "Review the official Terms of Service for Write Assist. By using our service, you agree to these terms.",
    robots: "noindex, follow",
};

export default function TermsPage() {
    return (
        <div className="bg-white py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-3xl mx-auto">
                <div className="text-center mb-12">
                    <h1 className="text-4xl font-bold text-gray-900">Terms of Service</h1>
                    <p className="mt-4 text-lg text-gray-600">
                        Please read these terms carefully before using Write Assist
                    </p>
                </div>

                <div className="space-y-6 text-gray-700">
                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">1. Acceptance of Terms</h2>
                        <p>
                            By accessing and using Write Assist, you accept and agree to be bound by the terms and
                            provision of this agreement.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">2. Use License</h2>
                        <p>
                            Permission is granted to temporarily use Write Assist for personal, non-commercial purposes.
                            This is the grant of a license, not a transfer of title.
                        </p>
                        <p>
                            You may not use the service for any illegal or unauthorized purpose. You agree not to
                            violate any laws in your jurisdiction.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">3. Service Limitations</h2>
                        <p>
                            Write Assist provides a daily limit of 30 free text refinements per user. This limit is
                            enforced to ensure fair usage and service availability for all users.
                        </p>
                        <p>
                            We reserve the right to modify or discontinue the service at any time without prior notice.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">4. Disclaimer</h2>
                        <p>
                            The materials on Write Assist are provided on an "as is" basis. Write Assist makes no
                            warranties, expressed or implied, and hereby disclaims and negates all other warranties.
                        </p>
                        <p>
                            We do not guarantee the accuracy, completeness, or usefulness of any refined text generated
                            by our service. Users are responsible for reviewing and verifying all generated content.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">5. Limitations</h2>
                        <p>
                            In no event shall Write Assist or its suppliers be liable for any damages (including,
                            without limitation, damages for loss of data or profit, or due to business interruption)
                            arising out of the use or inability to use the service.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">6. Revisions</h2>
                        <p>
                            Write Assist may revise these terms of service at any time without notice. By using this
                            service, you are agreeing to be bound by the then current version of these terms of service.
                        </p>
                    </section>

                    <section>
                        <h2 className="text-2xl font-semibold text-gray-900 mb-4">7. Contact Information</h2>
                        <p>
                            If you have any questions about these Terms of Service, please contact us at:{" "}
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
