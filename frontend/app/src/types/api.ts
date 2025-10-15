// API 요청 및 응답에 대한 타입 정의

// 1. TextCorrection API
export type Tone = 'VERY_CASUAL' | 'CASUAL' | 'STANDARD' | 'FORMAL' | 'VERY_FORMAL';
export type Purpose = 'INFORMATION' | 'PERSUASION_REQUEST' | 'APOLOGY_REFUSAL' | 'THANKS_PRAISE' | 'FREEFORM';
export type LengthType = 'SHORT' | 'STANDARD' | 'LONG';
export type StyleType = 'CONCISE_CLEAR' | 'EMOTIONAL_NATURAL' | 'PROFESSIONAL_ACADEMIC';

export interface TextCorrectionRequest {
  text: string;
  tone: Tone;
  purpose: Purpose;
  lengthType: LengthType;
  styleType: StyleType;
}

export interface TextCorrectionResponse {
  originalText: string;
  correctedTexts: string[];
  remainingUsage: number;
}

// 2. Usage API
export interface UsageResponse {
  remainingUsage: number;
  dailyLimit: number;
  usedToday: number;
}

// 3. Preset API
interface ToneInfo {
  value: Tone;
  description: string;
}
interface PurposeInfo {
  value: Purpose;
  description: string;
}
interface LengthTypeInfo {
  value: LengthType;
  description: string;
}
interface StyleTypeInfo {
  value: StyleType;
  description: string;
}

export interface PresetResponse {
  id: number;
  name: string;
  description: string;
  tone: ToneInfo;
  purpose: PurposeInfo;
  lengthType: LengthTypeInfo;
  styleType: StyleTypeInfo;
}

// 4. FAQ API
export interface FaqResponse {
  id: number;
  question: string;
  answer: string;
  sortOrder: number;
}

// 5. 공통 API 응답 래퍼
interface ErrorDetail {
  code: string;
  field?: string;
  rejectedValue?: any;
}

export interface ApiResponse<T> {
  success: boolean;
  data: T;
  message: string;
  error?: ErrorDetail | null;
}
