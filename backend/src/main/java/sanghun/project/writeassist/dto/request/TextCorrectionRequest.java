package sanghun.project.writeassist.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @NotBlank(message = "Text to correct cannot be empty.")
    @Size(max = 1000, message = "Text cannot exceed 1000 characters.")
    private String text;

    @NotNull(message = "Tone must be specified.")
    private Tone tone;

    @NotNull(message = "Purpose must be specified.")
    private Purpose purpose;

    @NotNull(message = "Length type must be specified.")
    private LengthType lengthType;

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

