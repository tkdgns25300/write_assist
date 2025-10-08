package sanghun.project.writeassist.controller.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sanghun.project.writeassist.dto.response.FaqResponse;
import sanghun.project.writeassist.service.FaqService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
@DisplayName("FaqController 테스트")
class FaqControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaqService faqService;

    @Test
    @DisplayName("GET /api/faq - 활성화된 FAQ 목록 조회 성공")
    void getFaqs_Success() throws Exception {
        // given
        List<FaqResponse> mockFaqs = Arrays.asList(
            createMockFaqResponse(1, "Is Write Assist really free?", "Yes, completely free.", 1),
            createMockFaqResponse(2, "Is there a usage limit?", "Yes, daily limit applies.", 2),
            createMockFaqResponse(3, "Why are ads displayed?", "To cover operational costs.", 3)
        );

        given(faqService.getActiveFaqs()).willReturn(mockFaqs);

        // when & then
        mockMvc.perform(get("/api/faq")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("FAQ 목록을 성공적으로 조회했습니다."))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()").value(3))
            .andExpect(jsonPath("$.data[0].id").value(1))
            .andExpect(jsonPath("$.data[0].question").value("Is Write Assist really free?"))
            .andExpect(jsonPath("$.data[0].answer").value("Yes, completely free."))
            .andExpect(jsonPath("$.data[0].sortOrder").value(1))
            .andExpect(jsonPath("$.data[1].id").value(2))
            .andExpect(jsonPath("$.data[1].sortOrder").value(2))
            .andExpect(jsonPath("$.data[2].id").value(3));
    }

    @Test
    @DisplayName("GET /api/faq - FAQ가 없을 때 빈 배열 반환")
    void getFaqs_EmptyList() throws Exception {
        // given
        given(faqService.getActiveFaqs()).willReturn(Arrays.asList());

        // when & then
        mockMvc.perform(get("/api/faq")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()").value(0));
    }

    private FaqResponse createMockFaqResponse(Integer id, String question, String answer, Integer sortOrder) {
        return FaqResponse.builder()
            .id(id)
            .question(question)
            .answer(answer)
            .sortOrder(sortOrder)
            .build();
    }
}

