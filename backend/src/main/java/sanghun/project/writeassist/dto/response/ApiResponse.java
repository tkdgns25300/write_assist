package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "표준 API 응답 래퍼")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Schema(description = "성공 여부", example = "true")
    private final boolean success;

    @Schema(description = "응답 데이터 (성공 시에만 존재)")
    private final T data;

    @Schema(description = "응답 메시지", example = "Text correction completed successfully.")
    private final String message;

    @Schema(description = "에러 상세 정보 (실패 시에만 존재)")
    private final ErrorDetail error;

    private ApiResponse(boolean success, T data, String message, ErrorDetail error) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    /**
     * 성공 응답 (데이터 + 메시지)
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message, null);
    }

    /**
     * 성공 응답 (데이터만)
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null);
    }

    /**
     * 성공 응답 (메시지만)
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, null, message, null);
    }

    /**
     * 실패 응답 (에러 상세 정보 + 메시지)
     */
    public static <T> ApiResponse<T> error(String message, ErrorDetail errorDetail) {
        return new ApiResponse<>(false, null, message, errorDetail);
    }

    /**
     * 실패 응답 (메시지만)
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message, null);
    }

    /**
     * 에러 상세 정보
     */
    @Schema(description = "에러 상세 정보")
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorDetail {
        @Schema(description = "에러 코드", example = "U001")
        private final String code;

        @Schema(description = "에러 발생 필드 (Validation 에러 시)", example = "text")
        private final String field;

        @Schema(description = "거부된 값 (Validation 에러 시)", example = "")
        private final Object rejectedValue;

        public ErrorDetail(String code, String field, Object rejectedValue) {
            this.code = code;
            this.field = field;
            this.rejectedValue = rejectedValue;
        }

        public static ErrorDetail of(String code) {
            return new ErrorDetail(code, null, null);
        }

        public static ErrorDetail of(String code, String field, Object rejectedValue) {
            return new ErrorDetail(code, field, rejectedValue);
        }
    }
}

