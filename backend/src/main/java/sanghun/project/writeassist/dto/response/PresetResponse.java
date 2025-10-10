package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Preset;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;

@Schema(description = "프리셋 응답")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PresetResponse {

    @Schema(description = "프리셋 ID", example = "1")
    private Integer id;

    @Schema(description = "프리셋 이름", example = "Standard Business Email")
    private String name;

    @Schema(description = "프리셋 설명", example = "For professional communication with external partners or superiors.")
    private String description;

    @Schema(description = "톤 정보 (값 + 설명)")
    private ToneInfo tone;

    @Schema(description = "목적 정보 (값 + 설명)")
    private PurposeInfo purpose;

    @Schema(description = "분량 정보 (값 + 설명)")
    private LengthTypeInfo lengthType;

    @Schema(description = "스타일 정보 (값 + 설명)")
    private StyleTypeInfo styleType;

    public static PresetResponse from(Preset preset) {
        return PresetResponse.builder()
            .id(preset.getId())
            .name(preset.getName())
            .description(preset.getDescription())
            .tone(ToneInfo.from(preset.getTone()))
            .purpose(PurposeInfo.from(preset.getPurpose()))
            .lengthType(LengthTypeInfo.from(preset.getLengthType()))
            .styleType(StyleTypeInfo.from(preset.getStyleType()))
            .build();
    }

    @Getter
    @Builder
    public static class ToneInfo {
        private String value;
        private String description;

        public static ToneInfo from(Tone tone) {
            return ToneInfo.builder()
                .value(tone.name())
                .description(tone.getDescription())
                .build();
        }
    }

    @Getter
    @Builder
    public static class PurposeInfo {
        private String value;
        private String description;

        public static PurposeInfo from(Purpose purpose) {
            return PurposeInfo.builder()
                .value(purpose.name())
                .description(purpose.getDescription())
                .build();
        }
    }

    @Getter
    @Builder
    public static class LengthTypeInfo {
        private String value;
        private String description;

        public static LengthTypeInfo from(LengthType lengthType) {
            return LengthTypeInfo.builder()
                .value(lengthType.name())
                .description(lengthType.getDescription())
                .build();
        }
    }

    @Getter
    @Builder
    public static class StyleTypeInfo {
        private String value;
        private String description;

        public static StyleTypeInfo from(StyleType styleType) {
            return StyleTypeInfo.builder()
                .value(styleType.name())
                .description(styleType.getDescription())
                .build();
        }
    }
}

