package sanghun.project.writeassist.dto.vertexai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VertexAiResponse {

    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;
    private String modelVersion;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Candidate {
        private Integer index;
        private Content content;
        private String finishReason;
        private Double avgLogprobs;
        private List<SafetyRating> safetyRatings;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        private List<Part> parts;
        private String role;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Part {
        private String text;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SafetyRating {
        private String category;
        private String probability;
        private Boolean blocked;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UsageMetadata {
        private Integer promptTokenCount;
        private Integer candidatesTokenCount;
        private Integer totalTokenCount;
        private String trafficType;
        private Integer thoughtsTokenCount;
    }

    /**
     * 모든 candidate의 텍스트를 추출합니다.
     *
     * @return 교정된 텍스트 리스트
     */
    public List<String> getTexts() {
        if (candidates == null || candidates.isEmpty()) {
            return List.of();
        }

        return candidates.stream()
            .filter(candidate -> candidate.getContent() != null)
            .filter(candidate -> candidate.getContent().getParts() != null)
            .filter(candidate -> !candidate.getContent().getParts().isEmpty())
            .map(candidate -> candidate.getContent().getParts().get(0).getText())
            .filter(text -> text != null && !text.isEmpty())
            .collect(Collectors.toList());
    }

    /**
     * 모든 finishReason을 확인합니다.
     *
     * @return finishReason 리스트
     */
    public List<String> getFinishReasons() {
        if (candidates == null || candidates.isEmpty()) {
            return List.of();
        }

        return candidates.stream()
            .map(Candidate::getFinishReason)
            .collect(Collectors.toList());
    }

    /**
     * 안전 차단 여부를 확인합니다.
     *
     * @return 차단된 경우 true
     */
    public boolean isBlocked() {
        if (candidates == null || candidates.isEmpty()) {
            return false;
        }

        return candidates.stream()
            .anyMatch(candidate -> 
                "FINISH_REASON_SAFETY".equals(candidate.getFinishReason()) ||
                "FINISH_REASON_PROHIBITED_CONTENT".equals(candidate.getFinishReason()) ||
                "FINISH_REASON_BLOCKLIST".equals(candidate.getFinishReason())
            );
    }

    /**
     * 에러 메시지를 생성합니다.
     *
     * @return 사용자용 에러 메시지
     */
    public String getErrorMessage() {
        if (candidates == null || candidates.isEmpty()) {
            return "No response generated.";
        }

        String finishReason = candidates.get(0).getFinishReason();

        if (finishReason == null || "STOP".equals(finishReason)) {
            return null;
        }

        switch (finishReason) {
            case "FINISH_REASON_SAFETY":
                return "Content was blocked due to safety concerns. Please rephrase your text.";
            case "FINISH_REASON_MAX_TOKENS":
                return "Text is too long to process. Please shorten your input.";
            case "FINISH_REASON_RECITATION":
                return "Content contains copyrighted material. Please use original text.";
            case "FINISH_REASON_BLOCKLIST":
                return "Text contains prohibited terms. Please modify your input.";
            case "FINISH_REASON_PROHIBITED_CONTENT":
                return "Content violates usage policies. Please use appropriate text.";
            case "FINISH_REASON_SPII":
                return "Text contains sensitive personal information. Please remove it.";
            default:
                return "Failed to generate correction. Please try again.";
        }
    }
}

