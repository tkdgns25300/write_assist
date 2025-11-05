import { SlidersHorizontal, Target, Scale, PenTool, BotMessageSquare } from "lucide-react";

export default function FeatureSection() {
    const features = [
        {
            icon: <SlidersHorizontal className="h-8 w-8 text-blue-600" />,
            title: "Fine-Tune Your Tone",
            description: "From extremely friendly to highly formal, our Tone slider lets you precisely adjust the level of politeness and formality. Ensure your message always strikes the right chord.",
        },
        {
            icon: <Target className="h-8 w-8 text-blue-600" />,
            title: "Define Your Purpose",
            description: "Clearly state your goal—whether it's to inform, persuade, apologize, or praise. Our AI structures your text to effectively achieve your intended purpose.",
        },
        {
            icon: <Scale className="h-8 w-8 text-blue-600" />,
            title: "Control the Length",
            description: "Easily summarize long texts into concise points or expand on simple ideas to provide detailed explanations. Control the length of your content with a single click.",
        },
        {
            icon: <PenTool className="h-8 w-8 text-blue-600" />,
            title: "Adjust the Style",
            description: "Switch between different writing styles, from concise and clear for professional reports to emotive and natural for creative storytelling. Match your text's mood to your audience.",
        },
    ];

    return (
        <section className="py-16 sm:py-24">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="text-center">
                    <h2 className="text-base font-semibold text-blue-600 tracking-wide uppercase">
                        How It Works
                    </h2>
                    <p className="mt-2 text-3xl font-extrabold text-gray-900 tracking-tight sm:text-4xl">
                        Unlock the Power of Contextual Writing
                    </p>
                    <p className="mt-5 max-w-2xl mx-auto text-xl text-gray-500">
                        Write Assist is more than just a proofreader. It's your AI writing partner that understands context, purpose, and audience to help you craft the perfect message every time.
                    </p>
                </div>

                <div className="mt-20">
                    <dl className="grid grid-cols-1 gap-x-8 gap-y-16 lg:grid-cols-2">
                        {features.map((feature) => (
                            <div key={feature.title} className="relative flex items-start">
                                <div className="flex-shrink-0">
                                    <div className="flex items-center justify-center h-16 w-16 rounded-lg bg-blue-50">
                                        {feature.icon}
                                    </div>
                                </div>
                                <div className="ml-6">
                                    <dt className="text-2xl font-bold leading-6 text-gray-900">
                                        {feature.title}
                                    </dt>
                                    <dd className="mt-2 text-lg text-gray-500">
                                        {feature.description}
                                    </dd>
                                </div>
                            </div>
                        ))}
                    </dl>
                </div>

                <div className="mt-24 bg-gray-50 rounded-2xl p-12">
                    <div className="max-w-3xl mx-auto text-center">
                        <div className="flex justify-center">
                            <BotMessageSquare className="h-12 w-12 text-blue-600" />
                        </div>
                        <h3 className="mt-4 text-3xl font-extrabold text-gray-900">
                            Perfect for Every Occasion
                        </h3>
                        <div className="mt-6 text-left max-w-xl mx-auto">
                            <ul className="space-y-4 text-lg text-gray-600">
                                <li className="flex items-start">
                                    <span className="text-blue-600 font-bold mr-3 mt-1 flex-shrink-0">✓</span>
                                    <span><span className="font-semibold">Professionals</span> drafting critical reports or formal emails.</span>
                                </li>
                                <li className="flex items-start">
                                    <span className="text-blue-600 font-bold mr-3 mt-1 flex-shrink-0">✓</span>
                                    <span><span className="font-semibold">Students</span> polishing essays and academic papers.</span>
                                </li>
                                <li className="flex items-start">
                                    <span className="text-blue-600 font-bold mr-3 mt-1 flex-shrink-0">✓</span>
                                    <span><span className="font-semibold">Job Seekers</span> creating compelling cover letters and resumes.</span>
                                </li>
                                <li className="flex items-start">
                                    <span className="text-blue-600 font-bold mr-3 mt-1 flex-shrink-0">✓</span>
                                    <span><span className="font-semibold">Non-native Speakers</span> aiming for natural and fluent phrasing.</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}
