package sanghun.project.writeassist.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanghun.project.writeassist.dto.response.ApiResponse;
import sanghun.project.writeassist.dto.response.FaqResponse;
import sanghun.project.writeassist.service.FaqService;

import java.util.List;

@Tag(name = "FAQ", description = "자주 묻는 질문 API")
@Slf4j
@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    /**
     * 활성화된 FAQ 목록을 조회합니다.
     *
     * @return FAQ 목록 응답
     */
    @Operation(
        summary = "FAQ 목록 조회",
        description = "활성화된 FAQ 목록을 정렬 순서대로 조회합니다.\n\n" +
            "**주요 FAQ:**\n" +
            "- Write Assist는 정말 무료인가요?\n" +
            "- 무료인데 혹시 사용 횟수 제한이 있나요?\n" +
            "- 광고는 왜 표시되나요?\n\n" +
            "FAQ는 관리자가 설정한 순서대로 반환됩니다."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "FAQ 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<FaqResponse>>> getFaqs() {
        log.info("GET /api/faq - Fetching active FAQs");

        List<FaqResponse> faqs = faqService.getActiveFaqs();

        log.info("Returning {} active FAQs", faqs.size());

        return ResponseEntity.ok(
            ApiResponse.success(faqs, "Successfully retrieved FAQ list.")
        );
    }
}

