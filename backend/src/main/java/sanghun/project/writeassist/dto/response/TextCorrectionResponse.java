package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "텍스트 교정 응답")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextCorrectionResponse {

    @Schema(description = "원본 텍스트 (줄바꿈, 들여쓰기 등 포맷팅 포함)", example = "안녕하세요.\n오늘 회의 하실래요?\n시간은 오후 2시입니다.")
    private String originalText;

    @Schema(description = "AI가 생성한 교정된 텍스트 배열 (최대 3개, 중복 제거됨, 원본 포맷팅 유지)", 
            example = "[\"안녕하세요.\\n오늘 회의하시겠습니까?\\n시간은 오후 2시입니다.\", \"안녕하세요.\\n오늘 회의를 진행하시겠습니까?\\n시간은 오후 2시입니다.\"]")
    private List<String> correctedTexts;

    @Schema(description = "오늘 남은 사용 가능 횟수 (0~30)", example = "29")
    private Integer remainingUsage;

    public static TextCorrectionResponse of(String originalText, List<String> correctedTexts, Integer remainingUsage) {
        return TextCorrectionResponse.builder()
            .originalText(originalText)
            .correctedTexts(correctedTexts)
            .remainingUsage(remainingUsage)
            .build();
    }
}

