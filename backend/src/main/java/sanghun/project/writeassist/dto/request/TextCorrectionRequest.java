package sanghun.project.writeassist.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextCorrectionRequest {

    @Schema(description = "교정할 텍스트 (최대 1000자)", example = "회의 일정 조율하고 싶어요", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Text to correct cannot be empty.")
    @Size(max = 1000, message = "Text cannot exceed 1000 characters.")
    private String text;

    @Schema(description = "격식 수준 (1: 매우 친근함 ~ 5: 매우 정중함)", example = "FORMAL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Tone must be specified.")
    private Tone tone;

    @Schema(description = "글의 목적 (정보 전달, 설득/요청, 사과/거절, 감사/칭찬, 자유)", example = "INFORMATION", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Purpose must be specified.")
    private Purpose purpose;

    @Schema(description = "분량 조절 (짧게, 표준, 길게)", example = "STANDARD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Length type must be specified.")
    private LengthType lengthType;

    @Schema(description = "문체 스타일 (간결·명쾌, 감성·자연스러움, 전문·학술적)", example = "CONCISE_CLEAR", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Style type must be specified.")
    private StyleType styleType;

    @Builder
    public TextCorrectionRequest(String text, Tone tone, Purpose purpose, LengthType lengthType, StyleType styleType) {
        this.text = text;
        this.tone = tone;
        this.purpose = purpose;
        this.lengthType = lengthType;
        this.styleType = styleType;
    }
}

