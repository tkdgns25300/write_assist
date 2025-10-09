package sanghun.project.writeassist.controller.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanghun.project.writeassist.dto.request.TextCorrectionRequest;
import sanghun.project.writeassist.dto.response.ApiResponse;
import sanghun.project.writeassist.dto.response.TextCorrectionResponse;
import sanghun.project.writeassist.service.TextCorrectionService;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/correct")
@RequiredArgsConstructor
public class TextCorrectionController {

    private static final String UUID_COOKIE_NAME = "user_uuid";
    private static final int COOKIE_MAX_AGE = 365 * 24 * 60 * 60; // 1년

    private final TextCorrectionService textCorrectionService;

    /**
     * 텍스트를 교정합니다.
     *
     * @param request      교정 요청 데이터
     * @param httpRequest  HTTP 요청
     * @param httpResponse HTTP 응답
     * @return 교정 결과
     */
    @PostMapping
    public ResponseEntity<ApiResponse<TextCorrectionResponse>> correctText(
        @Valid @RequestBody TextCorrectionRequest request,
        HttpServletRequest httpRequest,
        HttpServletResponse httpResponse
    ) {
        log.info("POST /api/correct - Text correction request received");

        // 1. UUID 쿠키 확인 또는 생성
        String userUuid = getOrCreateUserUuid(httpRequest, httpResponse);

        // 2. User-Agent 추출
        String userAgent = httpRequest.getHeader("User-Agent");
        if (userAgent == null || userAgent.isEmpty()) {
            userAgent = "Unknown";
        }

        log.info("Processing correction for UUID: {}", userUuid);

        // 3. 텍스트 교정 처리
        TextCorrectionResponse response = textCorrectionService.correctText(request, userUuid, userAgent);

        log.info("Text correction completed successfully");

        return ResponseEntity.ok(
            ApiResponse.success(response, "Text correction completed successfully.")
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

