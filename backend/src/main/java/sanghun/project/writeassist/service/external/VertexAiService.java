package sanghun.project.writeassist.service.external;

import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import sanghun.project.writeassist.dto.vertexai.VertexAiRequest;
import sanghun.project.writeassist.dto.vertexai.VertexAiResponse;
import sanghun.project.writeassist.exception.BusinessException;
import sanghun.project.writeassist.exception.ErrorCode;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VertexAiService {

    private final RestTemplate restTemplate;
    private final GoogleCredentials googleCredentials;

    @Value("${google.cloud.project-id}")
    private String projectId;

    @Value("${google.cloud.location}")
    private String location;

    @Value("${google.cloud.model-id}")
    private String modelId;

    /**
     * Vertex AI를 호출하여 텍스트를 생성합니다.
     *
     * @param prompt      생성된 프롬프트
     * @param temperature temperature 값
     * @return 생성된 텍스트 리스트 (최대 3개, 중복 제거됨)
     */
    public List<String> generateContent(String prompt, double temperature) {
        log.info("Calling Vertex AI - Model: {}, Temperature: {}", modelId, temperature);

        try {
            // 1. 요청 URL 생성
            String url = buildApiUrl();

            // 2. 요청 헤더 생성
            HttpHeaders headers = createHeaders();

            // 3. 요청 바디 생성
            VertexAiRequest request = VertexAiRequest.of(prompt, temperature);

            // 4. HTTP 요청 생성
            HttpEntity<VertexAiRequest> httpEntity = new HttpEntity<>(request, headers);

            log.debug("Sending request to Vertex AI: {}", url);

            // 5. API 호출
            ResponseEntity<VertexAiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                VertexAiResponse.class
            );

            VertexAiResponse responseBody = response.getBody();

            if (responseBody == null) {
                log.error("Vertex AI returned null response");
                throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "Empty response from AI service");
            }

            // 6. 응답 검증
            validateResponse(responseBody);

            // 7. 텍스트 추출 (중복 제거)
            List<String> texts = responseBody.getTexts();
            List<String> uniqueTexts = texts.stream()
                .distinct()
                .toList();

            log.info("Successfully generated {} unique versions (from {} candidates)",
                uniqueTexts.size(), texts.size());
            log.debug("Token usage - Prompt: {}, Candidates: {}, Total: {}",
                responseBody.getUsageMetadata().getPromptTokenCount(),
                responseBody.getUsageMetadata().getCandidatesTokenCount(),
                responseBody.getUsageMetadata().getTotalTokenCount());

            return uniqueTexts;

        } catch (HttpClientErrorException e) {
            log.error("Vertex AI client error: {} - {}", e.getStatusCode(), e.getMessage());

            if (e.getStatusCode().value() == 401) {
                throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "Authentication failed. Please contact administrator.");
            } else if (e.getStatusCode().value() == 429) {
                throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "Too many requests. Please try again later.");
            }

            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "AI service request failed: " + e.getMessage());

        } catch (HttpServerErrorException e) {
            log.error("Vertex AI server error: {} - {}", e.getStatusCode(), e.getMessage());
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "AI service is temporarily unavailable.");

        } catch (IOException e) {
            log.error("Failed to get access token", e);
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "Authentication error: " + e.getMessage());

        } catch (Exception e) {
            log.error("Unexpected error during Vertex AI call", e);
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "Unexpected error: " + e.getMessage());
        }
    }

    /**
     * API URL을 생성합니다.
     */
    private String buildApiUrl() {
        return String.format(
            "https://aiplatform.googleapis.com/v1/projects/%s/locations/%s/publishers/google/models/%s:generateContent",
            projectId, location, modelId
        );
    }

    /**
     * HTTP 헤더를 생성합니다.
     */
    private HttpHeaders createHeaders() throws IOException {
        // Access Token 가져오기 (자동 갱신됨)
        String accessToken = googleCredentials.refreshAccessToken().getTokenValue();

        log.debug("Using access token (first 50 chars): {}...",
            accessToken.substring(0, Math.min(50, accessToken.length())));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        return headers;
    }

    /**
     * 응답을 검증하고 에러가 있으면 예외를 발생시킵니다.
     */
    private void validateResponse(VertexAiResponse response) {
        // 안전 필터 차단 체크
        if (response.isBlocked()) {
            String errorMessage = response.getErrorMessage();
            log.warn("Content blocked by safety filter: {}", errorMessage);
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, errorMessage);
        }

        // finishReason 체크
        String errorMessage = response.getErrorMessage();
        if (errorMessage != null) {
            log.warn("AI generation failed: {}", errorMessage);
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, errorMessage);
        }

        // 텍스트 존재 여부 체크
        List<String> texts = response.getTexts();
        if (texts.isEmpty()) {
            log.error("No text generated from Vertex AI");
            throw new BusinessException(ErrorCode.AI_SERVICE_ERROR, "No correction generated. Please try again.");
        }
    }
}

