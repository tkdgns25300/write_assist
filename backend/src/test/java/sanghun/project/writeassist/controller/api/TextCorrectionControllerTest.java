package sanghun.project.writeassist.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;
import sanghun.project.writeassist.dto.request.TextCorrectionRequest;
import sanghun.project.writeassist.dto.response.TextCorrectionResponse;
import sanghun.project.writeassist.exception.BusinessException;
import sanghun.project.writeassist.exception.ErrorCode;
import sanghun.project.writeassist.service.TextCorrectionService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TextCorrectionController.class)
@DisplayName("TextCorrectionController 테스트")
class TextCorrectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TextCorrectionService textCorrectionService;

    @Test
    @DisplayName("POST /api/correct - 정상적인 텍스트 교정 성공")
    void correctText_Success() throws Exception {
        // given
        TextCorrectionRequest request = TextCorrectionRequest.builder()
            .text("회의 일정 조율하고 싶어요")
            .tone(Tone.FORMAL)
            .purpose(Purpose.INFORMATION)
            .lengthType(LengthType.STANDARD)
            .styleType(StyleType.CONCISE_CLEAR)
            .build();

        List<String> correctedTexts = Arrays.asList(
            "회의 일정을 조율하고자 합니다.",
            "회의 일정 조율을 요청드립니다."
        );

        TextCorrectionResponse response = TextCorrectionResponse.of(
            request.getText(),
            correctedTexts,
            29
        );

        given(textCorrectionService.correctText(any(), anyString(), anyString()))
            .willReturn(response);

        // when & then
        mockMvc.perform(post("/api/correct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("Text correction completed successfully."))
            .andExpect(jsonPath("$.data.originalText").value("회의 일정 조율하고 싶어요"))
            .andExpect(jsonPath("$.data.correctedTexts").isArray())
            .andExpect(jsonPath("$.data.correctedTexts.length()").value(2))
            .andExpect(jsonPath("$.data.remainingUsage").value(29));
    }

    @Test
    @DisplayName("POST /api/correct - 텍스트 없음 (Validation 실패)")
    void correctText_BlankText() throws Exception {
        // given
        TextCorrectionRequest request = TextCorrectionRequest.builder()
            .text("")
            .tone(Tone.FORMAL)
            .purpose(Purpose.INFORMATION)
            .lengthType(LengthType.STANDARD)
            .styleType(StyleType.CONCISE_CLEAR)
            .build();

        // when & then
        mockMvc.perform(post("/api/correct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.error.code").value("VALIDATION_ERROR"));
    }

    @Test
    @DisplayName("POST /api/correct - 텍스트 1000자 초과 (Validation 실패)")
    void correctText_TextTooLong() throws Exception {
        // given
        String longText = "a".repeat(1001);
        TextCorrectionRequest request = TextCorrectionRequest.builder()
            .text(longText)
            .tone(Tone.FORMAL)
            .purpose(Purpose.INFORMATION)
            .lengthType(LengthType.STANDARD)
            .styleType(StyleType.CONCISE_CLEAR)
            .build();

        // when & then
        mockMvc.perform(post("/api/correct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.error.code").value("VALIDATION_ERROR"));
    }

    @Test
    @DisplayName("POST /api/correct - 필수 파라미터 누락 (tone)")
    void correctText_MissingTone() throws Exception {
        // given
        String requestJson = """
            {
                "text": "회의 일정 조율하고 싶어요",
                "purpose": "INFORMATION",
                "lengthType": "STANDARD",
                "styleType": "CONCISE_CLEAR"
            }
            """;

        // when & then
        mockMvc.perform(post("/api/correct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    @DisplayName("POST /api/correct - 사용량 초과")
    void correctText_UsageLimitExceeded() throws Exception {
        // given
        TextCorrectionRequest request = TextCorrectionRequest.builder()
            .text("회의 일정 조율하고 싶어요")
            .tone(Tone.FORMAL)
            .purpose(Purpose.INFORMATION)
            .lengthType(LengthType.STANDARD)
            .styleType(StyleType.CONCISE_CLEAR)
            .build();

        doThrow(new BusinessException(ErrorCode.USAGE_LIMIT_EXCEEDED))
            .when(textCorrectionService)
            .correctText(any(), anyString(), anyString());

        // when & then
        mockMvc.perform(post("/api/correct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isTooManyRequests())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.error.code").value("U001"));
    }

    @Test
    @DisplayName("POST /api/correct - AI 서비스 에러")
    void correctText_AiServiceError() throws Exception {
        // given
        TextCorrectionRequest request = TextCorrectionRequest.builder()
            .text("회의 일정 조율하고 싶어요")
            .tone(Tone.FORMAL)
            .purpose(Purpose.INFORMATION)
            .lengthType(LengthType.STANDARD)
            .styleType(StyleType.CONCISE_CLEAR)
            .build();

        doThrow(new BusinessException(ErrorCode.AI_SERVICE_ERROR))
            .when(textCorrectionService)
            .correctText(any(), anyString(), anyString());

        // when & then
        mockMvc.perform(post("/api/correct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isServiceUnavailable())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.error.code").value("T003"));
    }
}

