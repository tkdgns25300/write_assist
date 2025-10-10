package sanghun.project.writeassist.dto.vertexai;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VertexAiRequest {

    private List<Content> contents;
    private GenerationConfig generationConfig;

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Content {
        private String role;
        private List<Part> parts;
    }

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Part {
        private String text;
    }

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GenerationConfig {
        private Double temperature;
        private Integer candidateCount;
    }

    /**
     * 프롬프트로부터 VertexAiRequest를 생성합니다.
     *
     * @param prompt      생성된 프롬프트
     * @param temperature temperature 값
     * @return VertexAiRequest 객체
     */
    public static VertexAiRequest of(String prompt, Double temperature) {
        Part part = Part.builder()
            .text(prompt)
            .build();

        Content content = Content.builder()
            .role("user")
            .parts(List.of(part))
            .build();

        GenerationConfig config = GenerationConfig.builder()
            .temperature(temperature)
            .candidateCount(3)
            .build();

        return VertexAiRequest.builder()
            .contents(List.of(content))
            .generationConfig(config)
            .build();
    }
}

