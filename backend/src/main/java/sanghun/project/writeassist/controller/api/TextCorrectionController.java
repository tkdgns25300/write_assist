package sanghun.project.writeassist.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import sanghun.project.writeassist.util.CookieUtils;

@Tag(name = "Text Correction", description = "AI 기반 텍스트 교정 API")
@Slf4j
@RestController
@RequestMapping("/api/correct")
@RequiredArgsConstructor
public class TextCorrectionController {

    private final TextCorrectionService textCorrectionService;

    /**
     * 텍스트를 교정합니다.
     *
     * @param request      교정 요청 데이터
     * @param httpRequest  HTTP 요청
     * @param httpResponse HTTP 응답
     * @return 교정 결과
     */
    @Operation(
        summary = "텍스트 교정",
        description = "AI를 이용하여 사용자의 텍스트를 교정합니다.\n\n" +
            "**특징:**\n" +
            "- 톤, 목적, 분량, 스타일 4가지 설정 가능\n" +
            "- 최대 3개의 교정된 버전 제공\n" +
            "- 일일 30회 사용 제한\n" +
            "- UUID 쿠키 자동 생성 (첫 방문 시)\n\n" +
            "**사용량 제한:**\n" +
            "- 일일 30회까지 무료 사용 가능\n" +
            "- 초과 시 429 에러 반환"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "교정 성공",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "입력 검증 실패 (텍스트 없음, 1000자 초과, 필수 파라미터 누락)",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "429",
            description = "일일 사용량 초과 (30회 제한)",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "503",
            description = "AI 서비스 에러 (일시적인 장애)",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<TextCorrectionResponse>> correctText(
        @Valid @RequestBody TextCorrectionRequest request,
        HttpServletRequest httpRequest,
        HttpServletResponse httpResponse
    ) {
        log.info("POST /api/correct - Text correction request received");

        // 1. UUID 쿠키 확인 또는 생성
        String userUuid = CookieUtils.getOrCreateUserUuid(httpRequest, httpResponse);

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
}

