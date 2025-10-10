package sanghun.project.writeassist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(apiInfo())
            .servers(apiServers());
    }

    private Info apiInfo() {
        return new Info()
            .title("Write Assist API")
            .description("AI 텍스트 교정 서비스 API 문서입니다.\n\n" +
                "이 API는 사용자의 텍스트를 AI를 이용하여 교정하고, " +
                "다양한 톤과 스타일로 변환하는 기능을 제공합니다.\n\n" +
                "**주요 기능:**\n" +
                "- 텍스트 교정 (톤, 목적, 분량, 스타일 조절)\n" +
                "- 사용량 관리 (일일 30회 제한)\n" +
                "- 프리셋 조회\n" +
                "- FAQ 조회\n\n" +
                "**인증 방식:** UUID 기반 쿠키 (자동 생성)")
            .version("1.0.0")
            .contact(new Contact()
                .name("Write Assist Team")
                .email("tkdgns25300@gmail.com"));
    }

    private List<Server> apiServers() {
        return List.of(
            new Server()
                .url("http://localhost:8003")
                .description("Development Server"),
            new Server()
                .url("https://writeassist.hun.it.co.kr")
                .description("Production Server")
        );
    }
}

