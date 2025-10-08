package sanghun.project.writeassist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sanghun.project.writeassist.domain.Faq;
import sanghun.project.writeassist.dto.response.FaqResponse;
import sanghun.project.writeassist.repository.FaqRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("FaqService 테스트")
class FaqServiceTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqService faqService;

    @Test
    @DisplayName("활성화된 FAQ를 정렬 순서대로 조회하고 FaqResponse로 변환한다")
    void getActiveFaqs_Success() {
        // given
        List<Faq> mockFaqs = Arrays.asList(
            createMockFaq(1, "Is Write Assist really free?", "Yes, completely free.", 1, true),
            createMockFaq(2, "Is there a usage limit?", "Yes, daily limit applies.", 2, true),
            createMockFaq(3, "Why are ads displayed?", "To cover operational costs.", 3, true)
        );

        given(faqRepository.findByIsActiveTrueOrderBySortOrderAsc()).willReturn(mockFaqs);

        // when
        List<FaqResponse> result = faqService.getActiveFaqs();

        // then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(0).getQuestion()).isEqualTo("Is Write Assist really free?");
        assertThat(result.get(0).getAnswer()).isEqualTo("Yes, completely free.");
        assertThat(result.get(0).getSortOrder()).isEqualTo(1);

        assertThat(result.get(1).getId()).isEqualTo(2);
        assertThat(result.get(1).getSortOrder()).isEqualTo(2);

        assertThat(result.get(2).getId()).isEqualTo(3);
        assertThat(result.get(2).getSortOrder()).isEqualTo(3);

        verify(faqRepository, times(1)).findByIsActiveTrueOrderBySortOrderAsc();
    }

    @Test
    @DisplayName("활성화된 FAQ가 없을 때 빈 리스트를 반환한다")
    void getActiveFaqs_EmptyList() {
        // given
        given(faqRepository.findByIsActiveTrueOrderBySortOrderAsc()).willReturn(Arrays.asList());

        // when
        List<FaqResponse> result = faqService.getActiveFaqs();

        // then
        assertThat(result).isEmpty();
        verify(faqRepository, times(1)).findByIsActiveTrueOrderBySortOrderAsc();
    }

    private Faq createMockFaq(Integer id, String question, String answer, Integer sortOrder, Boolean isActive) {
        Faq faq = Faq.builder()
            .question(question)
            .answer(answer)
            .sortOrder(sortOrder)
            .isActive(isActive)
            .build();

        // Reflection을 사용하여 ID 설정 (테스트용)
        try {
            java.lang.reflect.Field idField = Faq.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(faq, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return faq;
    }
}

