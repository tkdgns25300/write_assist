package sanghun.project.writeassist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Preset;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;
import sanghun.project.writeassist.dto.response.PresetResponse;
import sanghun.project.writeassist.repository.PresetRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("PresetService 테스트")
class PresetServiceTest {

    @Mock
    private PresetRepository presetRepository;

    @InjectMocks
    private PresetService presetService;

    @Test
    @DisplayName("모든 프리셋을 조회하고 PresetResponse로 변환한다")
    void getAllPresets_Success() {
        // given
        List<Preset> mockPresets = Arrays.asList(
            createMockPreset(1, "Standard Business Email", "For professional communication",
                Tone.FORMAL, Purpose.INFORMATION, LengthType.STANDARD, StyleType.CONCISE_CLEAR),
            createMockPreset(2, "Casual Colleague Chat", "For informal communication",
                Tone.VERY_CASUAL, Purpose.FREEFORM, LengthType.STANDARD, StyleType.EMOTIONAL_NATURAL),
            createMockPreset(3, "Polite Request or Refusal", "For making difficult requests",
                Tone.VERY_FORMAL, Purpose.PERSUASION_REQUEST, LengthType.LONG, StyleType.CONCISE_CLEAR),
            createMockPreset(4, "Report Draft Summary", "For summarizing documents",
                Tone.STANDARD, Purpose.INFORMATION, LengthType.SHORT, StyleType.PROFESSIONAL_ACADEMIC)
        );

        given(presetRepository.findAll()).willReturn(mockPresets);

        // when
        List<PresetResponse> result = presetService.getAllPresets();

        // then
        assertThat(result).hasSize(4);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Standard Business Email");
        assertThat(result.get(0).getTone().getValue()).isEqualTo("FORMAL");
        assertThat(result.get(0).getTone().getDescription()).isEqualTo("포멀");
        assertThat(result.get(0).getPurpose().getValue()).isEqualTo("INFORMATION");
        assertThat(result.get(0).getLengthType().getValue()).isEqualTo("STANDARD");
        assertThat(result.get(0).getStyleType().getValue()).isEqualTo("CONCISE_CLEAR");

        assertThat(result.get(1).getId()).isEqualTo(2);
        assertThat(result.get(1).getTone().getValue()).isEqualTo("VERY_CASUAL");
        assertThat(result.get(1).getStyleType().getValue()).isEqualTo("EMOTIONAL_NATURAL");

        verify(presetRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("프리셋이 없을 때 빈 리스트를 반환한다")
    void getAllPresets_EmptyList() {
        // given
        given(presetRepository.findAll()).willReturn(Arrays.asList());

        // when
        List<PresetResponse> result = presetService.getAllPresets();

        // then
        assertThat(result).isEmpty();
        verify(presetRepository, times(1)).findAll();
    }

    private Preset createMockPreset(Integer id, String name, String description,
                                     Tone tone, Purpose purpose,
                                     LengthType lengthType, StyleType styleType) {
        Preset preset = Preset.builder()
            .name(name)
            .description(description)
            .tone(tone)
            .purpose(purpose)
            .lengthType(lengthType)
            .styleType(styleType)
            .build();
        
        // Reflection을 사용하여 ID 설정 (테스트용)
        try {
            java.lang.reflect.Field idField = Preset.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(preset, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return preset;
    }
}

