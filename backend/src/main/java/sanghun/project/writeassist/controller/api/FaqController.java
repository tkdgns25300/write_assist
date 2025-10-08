package sanghun.project.writeassist.controller.api;

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

