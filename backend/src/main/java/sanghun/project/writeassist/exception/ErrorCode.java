package sanghun.project.writeassist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE("C001", "Invalid input value.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("C002", "Internal server error occurred.", HttpStatus.INTERNAL_SERVER_ERROR),

    // Usage
    USAGE_LIMIT_EXCEEDED("U001", "Daily usage limit exceeded.", HttpStatus.TOO_MANY_REQUESTS),
    INVALID_UUID("U002", "Invalid user identifier.", HttpStatus.BAD_REQUEST),

    // Correction
    TEXT_TOO_LONG("T001", "Text exceeds maximum length (1000 characters).", HttpStatus.BAD_REQUEST),
    TEXT_EMPTY("T002", "Please enter text to correct.", HttpStatus.BAD_REQUEST),
    AI_SERVICE_ERROR("T003", "AI service error occurred.", HttpStatus.SERVICE_UNAVAILABLE),

    // Preset
    PRESET_NOT_FOUND("P001", "Preset not found.", HttpStatus.NOT_FOUND),

    // FAQ
    FAQ_NOT_FOUND("F001", "FAQ not found.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}

