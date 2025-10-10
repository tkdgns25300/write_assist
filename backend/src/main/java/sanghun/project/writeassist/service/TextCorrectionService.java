package sanghun.project.writeassist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanghun.project.writeassist.dto.request.TextCorrectionRequest;
import sanghun.project.writeassist.dto.response.TextCorrectionResponse;
import sanghun.project.writeassist.service.external.VertexAiService;
import sanghun.project.writeassist.util.PromptBuilder;
import sanghun.project.writeassist.util.TemperatureMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextCorrectionService {

    private final UsageTrackingService usageTrackingService;
    private final VertexAiService vertexAiService;
    private final PromptBuilder promptBuilder;

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

        // 2. AI 서비스 호출
        List<String> correctedTexts = callAiService(request);

        log.info("Text correction completed. Generated {} unique versions", correctedTexts.size());

        return TextCorrectionResponse.of(request.getText(), correctedTexts);
    }

    /**
     * AI 서비스를 호출하여 텍스트를 교정합니다.
     *
     * @param request 교정 요청
     * @return 교정된 텍스트 리스트 (중복 제거됨)
     */
    private List<String> callAiService(TextCorrectionRequest request) {
        log.info("Calling AI service for text correction");

        // 1. 프롬프트 생성
        String prompt = promptBuilder.buildPrompt(
            request.getText(),
            request.getTone(),
            request.getPurpose(),
            request.getLengthType(),
            request.getStyleType()
        );

        log.debug("Generated prompt length: {} characters", prompt.length());

        // 2. Temperature 계산
        double temperature = TemperatureMapper.getTemperature(request.getStyleType());

        log.debug("Using temperature: {} for style: {}", temperature, request.getStyleType());

        // 3. Vertex AI 호출 (중복 제거 포함)
        List<String> correctedTexts = vertexAiService.generateContent(prompt, temperature);

        log.info("AI service returned {} unique corrections", correctedTexts.size());

        return correctedTexts;
    }
}

