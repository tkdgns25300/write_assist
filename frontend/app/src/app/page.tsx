import { getPresets, getUsage } from "@/lib/api";
import HomeClient from "@/components/home/HomeClient";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "Write Assist | AI-Powered Context Refinement Service",
    description: "Instantly generate perfect text tailored to your specific tone and purpose. Shorten your writing time with our AI, no login required.",
};

export default async function Home() {
    const initialPresets = await getPresets();
    const initialUsage = await getUsage();

    return <HomeClient initialPresets={initialPresets} initialUsage={initialUsage} />;
}
