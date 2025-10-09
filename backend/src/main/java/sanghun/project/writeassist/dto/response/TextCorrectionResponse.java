package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextCorrectionResponse {

    private String originalText;
    private String correctedText;
    private Integer remainingUsage;

    public static TextCorrectionResponse of(String originalText, String correctedText, Integer remainingUsage) {
        return TextCorrectionResponse.builder()
            .originalText(originalText)
            .correctedText(correctedText)
            .remainingUsage(remainingUsage)
            .build();
    }
}

