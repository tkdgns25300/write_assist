package sanghun.project.writeassist.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanghun.project.writeassist.dto.response.ApiResponse;
import sanghun.project.writeassist.dto.response.UsageResponse;
import sanghun.project.writeassist.service.UsageTrackingService;
import sanghun.project.writeassist.util.CookieUtils;

@Tag(name = "Usage Management", description = "사용량 관리 및 조회 API")
@Slf4j
@RestController
@RequestMapping("/api/usage")
@RequiredArgsConstructor
public class UsageController {

    private final UsageTrackingService usageTrackingService;

    /**
     * 남은 사용 가능 횟수를 조회합니다.
     *
     * @param httpRequest  HTTP 요청
     * @param httpResponse HTTP 응답
     * @return 사용량 정보
     */
    @Operation(
        summary = "남은 사용량 조회",
        description = "오늘 남은 사용 가능 횟수를 조회합니다.\n\n" +
            "**반환 정보:**\n" +
            "- remainingUsage: 남은 사용 가능 횟수 (0~30)\n" +
            "- dailyLimit: 일일 제한 (30회)\n" +
            "- usedToday: 오늘 사용한 횟수\n\n" +
            "**참고:**\n" +
            "- 사용량은 증가하지 않습니다 (조회만)\n" +
            "- UUID 쿠키 자동 생성 (첫 방문 시)"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "사용량 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        )
    })
    @GetMapping("/remaining")
    public ResponseEntity<ApiResponse<UsageResponse>> getRemainingUsage(
        HttpServletRequest httpRequest,
        HttpServletResponse httpResponse
    ) {
        log.info("GET /api/usage/remaining - Usage information request received");

        // 1. UUID 쿠키 확인 또는 생성
        String userUuid = CookieUtils.getOrCreateUserUuid(httpRequest, httpResponse);

        // 2. User-Agent 추출
        String userAgent = httpRequest.getHeader("User-Agent");
        if (userAgent == null || userAgent.isEmpty()) {
            userAgent = "Unknown";
        }

        log.info("Getting usage information for UUID: {}", userUuid);

        // 3. 남은 사용량 조회
        int remainingUsage = usageTrackingService.getRemainingUsage(userUuid, userAgent);
        int dailyLimit = usageTrackingService.getDailyUsageLimit();
        int usedToday = dailyLimit - remainingUsage;

        log.info("Usage info - Used: {}, Remaining: {}, Limit: {}", usedToday, remainingUsage, dailyLimit);

        // 4. 응답 생성
        UsageResponse response = UsageResponse.of(remainingUsage, dailyLimit, usedToday);

        return ResponseEntity.ok(
            ApiResponse.success(response, "Successfully retrieved usage information.")
        );
    }
}

