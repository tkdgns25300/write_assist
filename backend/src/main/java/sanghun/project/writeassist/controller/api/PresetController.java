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
import sanghun.project.writeassist.dto.response.PresetResponse;
import sanghun.project.writeassist.service.PresetService;

import java.util.List;

@Tag(name = "Preset", description = "프리셋 설정 조회 API")
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
    @Operation(
        summary = "프리셋 목록 조회",
        description = "미리 설정된 프리셋 목록을 조회합니다.\n\n" +
            "**프리셋 종류:**\n" +
            "- Standard Business Email (표준 업무 메일)\n" +
            "- Casual Colleague Chat (친한 동료 대화)\n" +
            "- Polite Request/Refusal (정중한 요청/거절)\n" +
            "- Report Draft Summary (보고서 초안)\n\n" +
            "각 프리셋은 톤, 목적, 분량, 스타일이 미리 조합되어 있습니다."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "프리셋 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<PresetResponse>>> getPresets() {
        log.info("GET /api/preset - Fetching all presets");

        List<PresetResponse> presets = presetService.getAllPresets();

        log.info("Returning {} presets", presets.size());

        return ResponseEntity.ok(
            ApiResponse.success(presets, "Successfully retrieved preset list.")
        );
    }
}

