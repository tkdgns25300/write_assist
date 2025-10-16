import { ApiResponse, PresetResponse, TextCorrectionRequest, TextCorrectionResponse, UsageResponse, FaqResponse } from "@/types/api";

const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;

async function fetcher<T>(url: string, options?: RequestInit): Promise<T> {
  const res = await fetch(`${API_BASE_URL}${url}`, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...options?.headers,
    },
  });

  if (!res.ok) {
    // API 레벨의 에러 처리 (e.g., 503, 429)
    const errorBody = await res.json();
    throw new Error(errorBody.message || 'An error occurred while fetching the data.');
  }

  const response: ApiResponse<T> = await res.json();
  if (!response.success) {
    // 애플리케이션 레벨의 에러 처리 (e.g., success: false)
    throw new Error(response.message || 'API returned a non-successful response.');
  }

  return response.data;
}

export const getPresets = (): Promise<PresetResponse[]> => {
  return fetcher<PresetResponse[]>('/preset');
};

export const getUsage = (): Promise<UsageResponse> => {
  return fetcher<UsageResponse>('/usage/remaining');
};

export const postCorrectText = (body: TextCorrectionRequest): Promise<TextCorrectionResponse> => {
  return fetcher<TextCorrectionResponse>('/correct', {
    method: 'POST',
    body: JSON.stringify(body),
  });
};

export const getFaqs = (): Promise<FaqResponse[]> => {
  return fetcher<FaqResponse[]>('/faq');
};
