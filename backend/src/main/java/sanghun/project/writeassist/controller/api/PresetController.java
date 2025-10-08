package sanghun.project.writeassist.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanghun.project.writeassist.dto.response.ApiResponse;
import sanghun.project.writeassist.dto.response.PresetResponse;
import sanghun.project.writeassist.service.PresetService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/preset")
@RequiredArgsConstructor
public class PresetController {

    private final PresetService presetService;

    /**
     * 모든 프리셋 목록을 조회합니다.
     *
     * @return 프리셋 목록 응답
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PresetResponse>>> getPresets() {
        log.info("GET /api/preset - Fetching all presets");

        List<PresetResponse> presets = presetService.getAllPresets();

        log.info("Returning {} presets", presets.size());

        return ResponseEntity.ok(
            ApiResponse.success(presets, "프리셋 목록을 성공적으로 조회했습니다.")
        );
    }
}

