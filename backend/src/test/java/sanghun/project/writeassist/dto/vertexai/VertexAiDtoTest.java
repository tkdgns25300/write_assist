package sanghun.project.writeassist.dto.vertexai;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@DisplayName("VertexAI DTO 직렬화/역직렬화 테스트")
class VertexAiDtoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("VertexAiRequest가 올바르게 JSON으로 직렬화된다")
    void serializeVertexAiRequest() throws Exception {
        // given
        VertexAiRequest request = VertexAiRequest.of("Test prompt", 0.3);

        // when
        String json = objectMapper.writeValueAsString(request);

        // then
        assertThat(json).contains("\"role\":\"user\"");
        assertThat(json).contains("\"text\":\"Test prompt\"");
        assertThat(json).contains("\"temperature\":0.3");
        assertThat(json).contains("\"candidateCount\":3");
    }

    @Test
    @DisplayName("VertexAiResponse가 올바르게 JSON에서 역직렬화된다")
    void deserializeVertexAiResponse() throws Exception {
        // given
        String json = """
            {
              "candidates": [
                {
                  "content": {
                    "parts": [
                      { "text": "회의 일정을 조율하고 싶습니다." }
                    ],
                    "role": "model"
                  },
                  "finishReason": "STOP"
                },
                {
                  "content": {
                    "parts": [
                      { "text": "회의 일정 조정이 필요합니다." }
                    ],
                    "role": "model"
                  },
                  "finishReason": "STOP"
                },
                {
                  "content": {
                    "parts": [
                      { "text": "회의 시간을 협의하고자 합니다." }
                    ],
                    "role": "model"
                  },
                  "finishReason": "STOP"
                }
              ],
              "usageMetadata": {
                "promptTokenCount": 520,
                "candidatesTokenCount": 45,
                "totalTokenCount": 565
              },
              "modelVersion": "gemini-2.5-flash-001"
            }
            """;

        // when
        VertexAiResponse response = objectMapper.readValue(json, VertexAiResponse.class);

        // then
        assertThat(response.getCandidates()).hasSize(3);
        assertThat(response.getTexts()).hasSize(3);
        assertThat(response.getTexts().get(0)).isEqualTo("회의 일정을 조율하고 싶습니다.");
        assertThat(response.getTexts().get(1)).isEqualTo("회의 일정 조정이 필요합니다.");
        assertThat(response.getTexts().get(2)).isEqualTo("회의 시간을 협의하고자 합니다.");
        assertThat(response.getUsageMetadata().getTotalTokenCount()).isEqualTo(565);
        assertThat(response.getModelVersion()).isEqualTo("gemini-2.5-flash-001");
    }

    @Test
    @DisplayName("getTexts() 메서드가 빈 리스트를 안전하게 처리한다")
    void getTexts_EmptyResponse() throws Exception {
        // given
        String json = """
            {
              "candidates": []
            }
            """;

        // when
        VertexAiResponse response = objectMapper.readValue(json, VertexAiResponse.class);

        // then
        assertThat(response.getTexts()).isEmpty();
    }

    @Test
    @DisplayName("FINISH_REASON_SAFETY 응답을 올바르게 처리한다")
    void handleSafetyBlock() throws Exception {
        // given
        String json = """
            {
              "candidates": [
                {
                  "content": {
                    "parts": [],
                    "role": "model"
                  },
                  "finishReason": "FINISH_REASON_SAFETY"
                }
              ]
            }
            """;

        // when
        VertexAiResponse response = objectMapper.readValue(json, VertexAiResponse.class);

        // then
        assertThat(response.isBlocked()).isTrue();
        assertThat(response.getErrorMessage()).isEqualTo("Content was blocked due to safety concerns. Please rephrase your text.");
    }

    @Test
    @DisplayName("정상 응답의 에러 메시지는 null이다")
    void getErrorMessage_NormalResponse() throws Exception {
        // given
        String json = """
            {
              "candidates": [
                {
                  "content": {
                    "parts": [
                      { "text": "corrected text" }
                    ]
                  },
                  "finishReason": "STOP"
                }
              ]
            }
            """;

        // when
        VertexAiResponse response = objectMapper.readValue(json, VertexAiResponse.class);

        // then
        assertThat(response.isBlocked()).isFalse();
        assertThat(response.getErrorMessage()).isNull();
    }
}

