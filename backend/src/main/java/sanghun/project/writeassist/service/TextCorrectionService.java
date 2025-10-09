package sanghun.project.writeassist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanghun.project.writeassist.dto.request.TextCorrectionRequest;
import sanghun.project.writeassist.dto.response.TextCorrectionResponse;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextCorrectionService {

    private final UsageTrackingService usageTrackingService;
    // TODO: AI Service 추가 예정

    /**
     * 텍스트를 교정합니다.
     *
     * @param request   교정 요청
     * @param userUuid  사용자 UUID
     * @param userAgent 사용자 에이전트
     * @return 교정 결과
     */
    @Transactional
    public TextCorrectionResponse correctText(TextCorrectionRequest request, String userUuid, String userAgent) {
        log.info("Correcting text for UUID: {}, Text length: {}", userUuid, request.getText().length());
        log.debug("Settings - Tone: {}, Purpose: {}, Length: {}, Style: {}",
            request.getTone(), request.getPurpose(), request.getLengthType(), request.getStyleType());

        // 1. 사용량 체크 및 증가
        usageTrackingService.checkAndIncrementUsage(userUuid, userAgent);

        // 2. AI 서비스 호출 (TODO: 추후 구현)
        String correctedText = callAiService(request);

        // 3. 남은 사용량 조회
        int remainingUsage = usageTrackingService.getRemainingUsage(userUuid, userAgent);

        log.info("Text correction completed. Remaining usage: {}", remainingUsage);

        return TextCorrectionResponse.of(request.getText(), correctedText, remainingUsage);
    }

    /**
     * AI 서비스를 호출하여 텍스트를 교정합니다.
     * TODO: Google Vertex API 연동 예정
     *
     * @param request 교정 요청
     * @return 교정된 텍스트
     */
    private String callAiService(TextCorrectionRequest request) {
        log.warn("AI service not implemented yet. Returning mock response.");

        // TODO: Google Vertex API 호출 로직
        // TODO: 프롬프트 생성 로직
        return "[MOCK] Corrected: " + request.getText();
    }
}

