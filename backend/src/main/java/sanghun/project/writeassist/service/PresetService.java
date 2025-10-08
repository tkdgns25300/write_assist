package sanghun.project.writeassist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanghun.project.writeassist.domain.Preset;
import sanghun.project.writeassist.dto.response.PresetResponse;
import sanghun.project.writeassist.repository.PresetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PresetService {

    private final PresetRepository presetRepository;

    /**
     * 모든 프리셋 목록을 조회합니다.
     *
     * @return 프리셋 응답 목록
     */
    public List<PresetResponse> getAllPresets() {
        log.info("Fetching all presets");

        List<Preset> presets = presetRepository.findAll();

        log.info("Found {} presets", presets.size());

        return presets.stream()
            .map(PresetResponse::from)
            .collect(Collectors.toList());
    }
}

