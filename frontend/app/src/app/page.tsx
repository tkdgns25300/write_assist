import { getPresets, getUsage } from "@/lib/api";
import HomeClient from "@/components/home/HomeClient";
import { Metadata } from "next";

export const metadata: Metadata = {
    title: "AI Text Refiner & Paraphrasing Tool | Write Assist",
    description: "Improve your writing instantly with Write Assist, an AI-powered text refiner and paraphrasing tool. Adjust tone, style, and purpose for free without registration.",
};

export default async function Home() {
    const initialPresets = await getPresets();
    const initialUsage = await getUsage();

    return <HomeClient initialPresets={initialPresets} initialUsage={initialUsage} />;
}
