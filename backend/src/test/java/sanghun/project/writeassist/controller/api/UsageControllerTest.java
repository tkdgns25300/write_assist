package sanghun.project.writeassist.controller.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sanghun.project.writeassist.service.UsageTrackingService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsageController.class)
@DisplayName("UsageController 테스트")
class UsageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsageTrackingService usageTrackingService;

    @Test
    @DisplayName("GET /api/usage/remaining - 첫 방문 시 전체 사용 가능 횟수 반환")
    void getRemainingUsage_FirstVisit() throws Exception {
        // given
        given(usageTrackingService.getRemainingUsage(anyString(), anyString())).willReturn(30);
        given(usageTrackingService.getDailyUsageLimit()).willReturn(30);

        // when & then
        mockMvc.perform(get("/api/usage/remaining")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("Successfully retrieved usage information."))
            .andExpect(jsonPath("$.data.remainingUsage").value(30))
            .andExpect(jsonPath("$.data.dailyLimit").value(30))
            .andExpect(jsonPath("$.data.usedToday").value(0));
    }

    @Test
    @DisplayName("GET /api/usage/remaining - 5회 사용 후 남은 횟수 반환")
    void getRemainingUsage_AfterUsage() throws Exception {
        // given
        given(usageTrackingService.getRemainingUsage(anyString(), anyString())).willReturn(25);
        given(usageTrackingService.getDailyUsageLimit()).willReturn(30);

        // when & then
        mockMvc.perform(get("/api/usage/remaining")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("Successfully retrieved usage information."))
            .andExpect(jsonPath("$.data.remainingUsage").value(25))
            .andExpect(jsonPath("$.data.dailyLimit").value(30))
            .andExpect(jsonPath("$.data.usedToday").value(5));
    }

    @Test
    @DisplayName("GET /api/usage/remaining - 모두 사용 후 0 반환")
    void getRemainingUsage_AllUsed() throws Exception {
        // given
        given(usageTrackingService.getRemainingUsage(anyString(), anyString())).willReturn(0);
        given(usageTrackingService.getDailyUsageLimit()).willReturn(30);

        // when & then
        mockMvc.perform(get("/api/usage/remaining")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.remainingUsage").value(0))
            .andExpect(jsonPath("$.data.dailyLimit").value(30))
            .andExpect(jsonPath("$.data.usedToday").value(30));
    }
}

