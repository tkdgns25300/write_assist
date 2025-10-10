package sanghun.project.writeassist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsageResponse {

    private Integer remainingUsage;
    private Integer dailyLimit;
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

