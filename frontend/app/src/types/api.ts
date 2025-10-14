// Error
export interface ErrorDetail {
    code: string;
    field: string;
    rejectedValue: object;
}

// Standard API Response Wrapper
export interface ApiResponse<T> {
    success: boolean;
    data: T;
    message: string;
    error?: ErrorDetail | null;
}

// FAQ
export interface Faq {
    id: number;
    question: string;
    answer: string;
    sortOrder: number;
}

export type FaqResponse = ApiResponse<Faq[]>;

// Preset
interface ToneInfo {
    value: string;
    description: string;
}

interface PurposeInfo {
    value: string;
    description: string;
}

interface LengthTypeInfo {
    value: string;
    description: string;
}

interface StyleTypeInfo {
    value: string;
    description: string;
}

export interface Preset {
    id: number;
    name: string;
    description: string;
    tone: ToneInfo;
    purpose: PurposeInfo;
    lengthType: LengthTypeInfo;
    styleType: StyleTypeInfo;
}

export type PresetResponse = ApiResponse<Preset[]>;

// Enum Types for TextCorrectionRequest for better type safety
export type Tone = "VERY_CASUAL" | "CASUAL" | "STANDARD" | "FORMAL" | "VERY_FORMAL";
export type Purpose = "INFORMATION" | "PERSUASION_REQUEST" | "APOLOGY_REFUSAL" | "THANKS_PRAISE" | "FREEFORM";
export type LengthType = "SHORT" | "STANDARD" | "LONG";
export type StyleType = "CONCISE_CLEAR" | "EMOTIONAL_NATURAL" | "PROFESSIONAL_ACADEMIC";

// Text Correction Request with specific string literal types
export interface TextCorrectionRequest {
    text: string;
    tone: Tone;
    purpose: Purpose;
    lengthType: LengthType;
    styleType: StyleType;
}

export interface TextCorrectionData {
    originalText: string;
    correctedTexts: string[];
    remainingUsage: number;
}

export type TextCorrectionResponse = ApiResponse<TextCorrectionData>;

// Usage Management
export interface UsageData {
    remainingUsage: number;
    dailyLimit: number;
    usedToday: number;
}

export type UsageResponse = ApiResponse<UsageData>;
