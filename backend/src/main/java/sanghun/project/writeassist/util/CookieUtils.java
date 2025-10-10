package sanghun.project.writeassist.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
public class CookieUtils {

    private static final String UUID_COOKIE_NAME = "user_uuid";
    private static final int COOKIE_MAX_AGE = 365 * 24 * 60 * 60; // 1년

    private CookieUtils() {
        // Utility class - private constructor
    }

    /**
     * 쿠키에서 UUID를 가져오거나 새로 생성합니다.
     *
     * @param request  HTTP 요청
     * @param response HTTP 응답
     * @return 사용자 UUID
     */
    public static String getOrCreateUserUuid(HttpServletRequest request, HttpServletResponse response) {
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

