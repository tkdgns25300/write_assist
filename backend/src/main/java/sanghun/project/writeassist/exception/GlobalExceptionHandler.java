package sanghun.project.writeassist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanghun.project.writeassist.dto.response.ApiResponse;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 비즈니스 예외 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        log.warn("Business exception occurred: {}", e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        ApiResponse.ErrorDetail errorDetail = ApiResponse.ErrorDetail.of(errorCode.getCode());

        ApiResponse<Void> response = ApiResponse.error(e.getMessage(), errorDetail);

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(response);
    }

    /**
     * @Valid 유효성 검증 실패 (MethodArgumentNotValidException)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Validation failed: {}", e.getMessage());

        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Invalid input value.";

        ApiResponse.ErrorDetail errorDetail = fieldError != null
            ? ApiResponse.ErrorDetail.of("VALIDATION_ERROR", fieldError.getField(), fieldError.getRejectedValue())
            : ApiResponse.ErrorDetail.of("VALIDATION_ERROR");

        ApiResponse<Void> response = ApiResponse.error(message, errorDetail);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }

    /**
     * BindException (Form 바인딩 실패)
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<Void>> handleBindException(BindException e) {
        log.warn("Binding failed: {}", e.getMessage());

        String message = e.getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));

        ApiResponse<Void> response = ApiResponse.error(message);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }

    /**
     * 모든 예외 처리 (최종 방어선)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Unexpected error occurred", e);

        ApiResponse.ErrorDetail errorDetail = ApiResponse.ErrorDetail.of("INTERNAL_SERVER_ERROR");
        ApiResponse<Void> response = ApiResponse.error("Internal server error occurred.", errorDetail);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }
}

