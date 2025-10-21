import { getPresets, getUsage } from "@/lib/api";
import HomeClient from "@/components/home/HomeClient";

export default async function Home() {
    const initialPresets = await getPresets();
    const initialUsage = await getUsage();

    return <HomeClient initialPresets={initialPresets} initialUsage={initialUsage} />;
}
