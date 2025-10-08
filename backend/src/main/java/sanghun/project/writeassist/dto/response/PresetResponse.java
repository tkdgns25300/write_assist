package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Preset;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PresetResponse {

    private Integer id;
    private String name;
    private String description;
    private ToneInfo tone;
    private PurposeInfo purpose;
    private LengthTypeInfo lengthType;
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

