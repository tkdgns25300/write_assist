package sanghun.project.writeassist.controller.api;

import jakarta.servlet.http.Cookie;
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

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/usage")
@RequiredArgsConstructor
public class UsageController {

    private static final String UUID_COOKIE_NAME = "user_uuid";
    private static final int COOKIE_MAX_AGE = 365 * 24 * 60 * 60; // 1년
    private static final int DAILY_USAGE_LIMIT = 10;

    private final UsageTrackingService usageTrackingService;

    /**
     * 남은 사용 가능 횟수를 조회합니다.
     *
     * @param httpRequest  HTTP 요청
     * @param httpResponse HTTP 응답
     * @return 사용량 정보
     */
    @GetMapping("/remaining")
    public ResponseEntity<ApiResponse<UsageResponse>> getRemainingUsage(
        HttpServletRequest httpRequest,
        HttpServletResponse httpResponse
    ) {
        log.info("GET /api/usage/remaining - Usage information request received");

        // 1. UUID 쿠키 확인 또는 생성
        String userUuid = getOrCreateUserUuid(httpRequest, httpResponse);

        // 2. User-Agent 추출
        String userAgent = httpRequest.getHeader("User-Agent");
        if (userAgent == null || userAgent.isEmpty()) {
            userAgent = "Unknown";
        }

        log.info("Getting usage information for UUID: {}", userUuid);

        // 3. 남은 사용량 조회
        int remainingUsage = usageTrackingService.getRemainingUsage(userUuid, userAgent);
        int usedToday = DAILY_USAGE_LIMIT - remainingUsage;

        log.info("Usage info - Used: {}, Remaining: {}, Limit: {}", usedToday, remainingUsage, DAILY_USAGE_LIMIT);

        // 4. 응답 생성
        UsageResponse response = UsageResponse.of(remainingUsage, DAILY_USAGE_LIMIT, usedToday);

        return ResponseEntity.ok(
            ApiResponse.success(response, "Successfully retrieved usage information.")
        );
    }

    /**
     * 쿠키에서 UUID를 가져오거나 새로 생성합니다.
     *
     * @param request  HTTP 요청
     * @param response HTTP 응답
     * @return 사용자 UUID
     */
    private String getOrCreateUserUuid(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String existingUuid = Arrays.stream(cookies)
                .filter(cookie -> UUID_COOKIE_NAME.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

            if (existingUuid != null && !existingUuid.isEmpty()) {
                log.debug("Using existing UUID from cookie: {}", existingUuid);
                return existingUuid;
            }
        }

        // 새로운 UUID 생성
        String newUuid = UUID.randomUUID().toString();
        log.info("Generated new UUID: {}", newUuid);

        // 쿠키 생성 및 저장
        Cookie cookie = new Cookie(UUID_COOKIE_NAME, newUuid);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return newUuid;
    }
}

