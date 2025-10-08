package sanghun.project.writeassist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanghun.project.writeassist.domain.Faq;
import sanghun.project.writeassist.dto.response.FaqResponse;
import sanghun.project.writeassist.repository.FaqRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FaqService {

    private final FaqRepository faqRepository;

    /**
     * 활성화된 FAQ 목록을 정렬 순서대로 조회합니다.
     *
     * @return FAQ 응답 목록
     */
    public List<FaqResponse> getActiveFaqs() {
        log.info("Fetching active FAQs");

        List<Faq> faqs = faqRepository.findByIsActiveTrueOrderBySortOrderAsc();

        log.info("Found {} active FAQs", faqs.size());

        return faqs.stream()
            .map(FaqResponse::from)
            .collect(Collectors.toList());
    }
}

