package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextCorrectionResponse {

    private String originalText;
    private List<String> correctedTexts;
    private Integer remainingUsage;

    public static TextCorrectionResponse of(String originalText, List<String> correctedTexts, Integer remainingUsage) {
        return TextCorrectionResponse.builder()
            .originalText(originalText)
            .correctedTexts(correctedTexts)
            .remainingUsage(remainingUsage)
            .build();
    }
}

