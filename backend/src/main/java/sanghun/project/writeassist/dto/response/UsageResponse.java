package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "사용량 정보 응답")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsageResponse {

    @Schema(description = "남은 사용 가능 횟수 (0~30)", example = "25")
    private Integer remainingUsage;

    @Schema(description = "일일 사용 제한 (30회)", example = "30")
    private Integer dailyLimit;

    @Schema(description = "오늘 사용한 횟수", example = "5")
    private Integer usedToday;

    /**
     * UsageResponse를 생성합니다.
     *
     * @param remainingUsage 남은 사용 가능 횟수
     * @param dailyLimit     일일 사용 제한 횟수
     * @param usedToday      오늘 사용한 횟수
     * @return UsageResponse 객체
     */
    public static UsageResponse of(Integer remainingUsage, Integer dailyLimit, Integer usedToday) {
        return UsageResponse.builder()
            .remainingUsage(remainingUsage)
            .dailyLimit(dailyLimit)
            .usedToday(usedToday)
            .build();
    }
}

