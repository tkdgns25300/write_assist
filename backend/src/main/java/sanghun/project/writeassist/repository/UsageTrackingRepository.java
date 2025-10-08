package sanghun.project.writeassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanghun.project.writeassist.domain.UsageTracking;

import java.time.LocalDate;
import java.util.Optional;

public interface UsageTrackingRepository extends JpaRepository<UsageTracking, Long> {

    /**
     * UUID, UserAgent, 사용 날짜로 사용량 추적 기록을 조회합니다.
     *
     * @param userUuid  사용자 UUID
     * @param userAgent 사용자 에이전트
     * @param usageDate 사용 날짜
     * @return 사용량 추적 기록
     */
    Optional<UsageTracking> findByUserUuidAndUserAgentAndUsageDate(
        String userUuid,
        String userAgent,
        LocalDate usageDate
    );

    /**
     * 특정 날짜 이전의 사용량 추적 기록을 삭제합니다.
     *
     * @param date 기준 날짜
     */
    void deleteByUsageDateBefore(LocalDate date);
}

