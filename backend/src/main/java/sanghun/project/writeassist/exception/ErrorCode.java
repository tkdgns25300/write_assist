package sanghun.project.writeassist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE("C001", "잘못된 입력값입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("C002", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // Usage
    USAGE_LIMIT_EXCEEDED("U001", "일일 사용 한도를 초과했습니다.", HttpStatus.TOO_MANY_REQUESTS),
    INVALID_UUID("U002", "유효하지 않은 사용자 식별자입니다.", HttpStatus.BAD_REQUEST),

    // Correction
    TEXT_TOO_LONG("T001", "텍스트가 최대 길이(1000자)를 초과했습니다.", HttpStatus.BAD_REQUEST),
    TEXT_EMPTY("T002", "교정할 텍스트를 입력해주세요.", HttpStatus.BAD_REQUEST),
    AI_SERVICE_ERROR("T003", "AI 서비스 호출 중 오류가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE),

    // Preset
    PRESET_NOT_FOUND("P001", "프리셋을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // FAQ
    FAQ_NOT_FOUND("F001", "FAQ를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}

