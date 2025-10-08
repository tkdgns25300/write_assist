package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import sanghun.project.writeassist.domain.Faq;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaqResponse {

    private Integer id;
    private String question;
    private String answer;
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

