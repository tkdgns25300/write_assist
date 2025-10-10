package sanghun.project.writeassist.controller.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;
import sanghun.project.writeassist.dto.response.PresetResponse;
import sanghun.project.writeassist.service.PresetService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PresetController.class)
@DisplayName("PresetController 테스트")
class PresetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PresetService presetService;

    @Test
    @DisplayName("GET /api/preset - 프리셋 목록 조회 성공")
    void getPresets_Success() throws Exception {
        // given
        List<PresetResponse> mockPresets = Arrays.asList(
            createMockPresetResponse(1, "Standard Business Email", "For professional communication",
                Tone.FORMAL, Purpose.INFORMATION, LengthType.STANDARD, StyleType.CONCISE_CLEAR),
            createMockPresetResponse(2, "Casual Colleague Chat", "For informal communication",
                Tone.VERY_CASUAL, Purpose.FREEFORM, LengthType.STANDARD, StyleType.EMOTIONAL_NATURAL)
        );

        given(presetService.getAllPresets()).willReturn(mockPresets);

        // when & then
        mockMvc.perform(get("/api/preset")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("Successfully retrieved preset list."))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()").value(2))
            .andExpect(jsonPath("$.data[0].id").value(1))
            .andExpect(jsonPath("$.data[0].name").value("Standard Business Email"))
            .andExpect(jsonPath("$.data[0].tone.value").value("FORMAL"))
            .andExpect(jsonPath("$.data[0].tone.description").value("Formal"))
            .andExpect(jsonPath("$.data[0].purpose.value").value("INFORMATION"))
            .andExpect(jsonPath("$.data[0].lengthType.value").value("STANDARD"))
            .andExpect(jsonPath("$.data[0].styleType.value").value("CONCISE_CLEAR"))
            .andExpect(jsonPath("$.data[1].id").value(2))
            .andExpect(jsonPath("$.data[1].name").value("Casual Colleague Chat"))
            .andExpect(jsonPath("$.data[1].tone.value").value("VERY_CASUAL"))
            .andExpect(jsonPath("$.data[1].styleType.value").value("EMOTIONAL_NATURAL"));
    }

    @Test
    @DisplayName("GET /api/preset - 프리셋이 없을 때 빈 배열 반환")
    void getPresets_EmptyList() throws Exception {
        // given
        given(presetService.getAllPresets()).willReturn(Arrays.asList());

        // when & then
        mockMvc.perform(get("/api/preset")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()").value(0));
    }

    private PresetResponse createMockPresetResponse(Integer id, String name, String description,
                                                     Tone tone, Purpose purpose,
                                                     LengthType lengthType, StyleType styleType) {
        return PresetResponse.builder()
            .id(id)
            .name(name)
            .description(description)
            .tone(PresetResponse.ToneInfo.from(tone))
            .purpose(PresetResponse.PurposeInfo.from(purpose))
            .lengthType(PresetResponse.LengthTypeInfo.from(lengthType))
            .styleType(PresetResponse.StyleTypeInfo.from(styleType))
            .build();
    }
}

