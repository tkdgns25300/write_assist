package sanghun.project.writeassist.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Configuration
public class VertexAiConfig {

    @Value("${google.cloud.project-id}")
    private String projectId;

    @Value("${google.cloud.location}")
    private String location;

    @Value("${google.cloud.credentials-path}")
    private Resource credentialsResource;

    /**
     * GoogleCredentials Bean 생성
     * - 토큰 자동 갱신 지원
     * - 만료 시 자동으로 재발급
     * - classpath: 리소스에서 자동으로 로드
     */
    @Bean
    public GoogleCredentials googleCredentials() throws IOException {
        log.info("Initializing Google Credentials from classpath resource");

        GoogleCredentials credentials = GoogleCredentials.fromStream(
                credentialsResource.getInputStream()
            )
            .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));

        // 토큰 자동 갱신 활성화
        credentials.refresh();

        log.info("Google Credentials initialized successfully");
        log.info("Access Token will be automatically refreshed when expired (1 hour)");

        return credentials;
    }

    /**
     * VertexAI Bean 생성
     */
    @Bean
    public VertexAI vertexAI(GoogleCredentials credentials) {
        log.info("Initializing Vertex AI - Project: {}, Location: {}", projectId, location);

        VertexAI.Builder builder = new VertexAI.Builder()
            .setLocation(location)
            .setProjectId(projectId)
            .setCredentials(credentials);

        VertexAI vertexAI = builder.build();

        log.info("Vertex AI initialized successfully");

        return vertexAI;
    }
}

