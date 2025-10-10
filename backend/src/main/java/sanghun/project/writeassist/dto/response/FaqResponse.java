package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import sanghun.project.writeassist.domain.Faq;

@Schema(description = "FAQ 응답")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaqResponse {

    @Schema(description = "FAQ ID", example = "1")
    private Integer id;

    @Schema(description = "질문", example = "Write Assist는 정말 무료인가요?")
    private String question;

    @Schema(description = "답변", example = "네, Write Assist는 완전히 무료로 운영됩니다.")
    private String answer;

    @Schema(description = "정렬 순서", example = "1")
    private Integer sortOrder;

    public static FaqResponse from(Faq faq) {
        return FaqResponse.builder()
            .id(faq.getId())
            .question(faq.getQuestion())
            .answer(faq.getAnswer())
            .sortOrder(faq.getSortOrder())
            .build();
    }
}

