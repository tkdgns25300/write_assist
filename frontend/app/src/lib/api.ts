import { PresetResponse, UsageResponse, TextCorrectionRequest, TextCorrectionResponse, FaqResponse } from "@/types/api";

const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;

// 에러 핸들링을 위한 헬퍼 함수
async function handleResponse<T>(response: Response): Promise<T> {
    if (!response.ok) {
        // TODO: 응답 상태에 따른 구체적인 에러 처리
        throw new Error(`API call failed with status: ${response.status}`);
    }
    const data = await response.json();
    // 백엔드에서 success: false를 보내는 경우도 에러로 처리
    if (data.success === false) {
        throw new Error(data.message || "API returned an error");
    }
    return data;
}

// GET: 프리셋 목록 조회
export const getPresets = async (): Promise<PresetResponse> => {
    const response = await fetch(`${API_BASE_URL}/preset`);
    return handleResponse<PresetResponse>(response);
};

// GET: FAQ 목록 조회
export const getFaqs = async (): Promise<FaqResponse> => {
    const response = await fetch(`${API_BASE_URL}/faq`);
    return handleResponse<FaqResponse>(response);
};

// GET: 사용량 조회
export const getUsage = async (): Promise<UsageResponse> => {
    const response = await fetch(`${API_BASE_URL}/usage`);
    return handleResponse<UsageResponse>(response);
};

// POST: 텍스트 교정 요청
export const postCorrectText = async (body: TextCorrectionRequest): Promise<TextCorrectionResponse> => {
    const response = await fetch(`${API_BASE_URL}/correct`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
    });
    return handleResponse<TextCorrectionResponse>(response);
};
