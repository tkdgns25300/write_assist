package sanghun.project.writeassist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanghun.project.writeassist.domain.UsageTracking;
import sanghun.project.writeassist.exception.BusinessException;
import sanghun.project.writeassist.exception.ErrorCode;
import sanghun.project.writeassist.repository.UsageTrackingRepository;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsageTrackingService {

    private static final int DAILY_USAGE_LIMIT = 30;

    private final UsageTrackingRepository usageTrackingRepository;

    /**
     * 사용량을 체크하고 사용 가능 여부를 확인합니다.
     *
     * @param userUuid  사용자 UUID
     * @param userAgent 사용자 에이전트
     * @throws BusinessException 사용량 초과 시
     */
    @Transactional
    public void checkAndIncrementUsage(String userUuid, String userAgent) {
        log.info("Checking usage for UUID: {}", userUuid);

        LocalDate today = LocalDate.now();
        Optional<UsageTracking> trackingOptional = usageTrackingRepository
            .findByUserUuidAndUserAgentAndUsageDate(userUuid, userAgent, today);

        UsageTracking tracking;

        if (trackingOptional.isPresent()) {
            tracking = trackingOptional.get();
            log.debug("Existing tracking found. Current usage: {}", tracking.getUsageCount());

            if (tracking.getUsageCount() >= DAILY_USAGE_LIMIT) {
                log.warn("Usage limit exceeded for UUID: {}", userUuid);
                throw new BusinessException(ErrorCode.USAGE_LIMIT_EXCEEDED);
            }

            tracking.incrementUsageCount();
        } else {
            log.debug("Creating new tracking record for UUID: {}", userUuid);
            tracking = UsageTracking.builder()
                .userUuid(userUuid)
                .userAgent(userAgent)
                .usageDate(today)
                .build();
            tracking.incrementUsageCount();
            usageTrackingRepository.save(tracking);
        }

        log.info("Usage incremented. Current count: {}/{}", tracking.getUsageCount(), DAILY_USAGE_LIMIT);
    }

    /**
     * 남은 사용 가능 횟수를 조회합니다.
     *
     * @param userUuid  사용자 UUID
     * @param userAgent 사용자 에이전트
     * @return 남은 사용 횟수
     */
    public int getRemainingUsage(String userUuid, String userAgent) {
        log.info("Getting remaining usage for UUID: {}", userUuid);

        LocalDate today = LocalDate.now();
        Optional<UsageTracking> trackingOptional = usageTrackingRepository
            .findByUserUuidAndUserAgentAndUsageDate(userUuid, userAgent, today);

        if (trackingOptional.isPresent()) {
            int usedCount = trackingOptional.get().getUsageCount();
            int remaining = DAILY_USAGE_LIMIT - usedCount;
            log.info("Used: {}, Remaining: {}", usedCount, remaining);
            return Math.max(0, remaining);
        }

        log.info("No usage record found. Remaining: {}", DAILY_USAGE_LIMIT);
        return DAILY_USAGE_LIMIT;
    }

    /**
     * 일일 사용 제한 횟수를 반환합니다.
     *
     * @return 일일 사용 제한 횟수
     */
    public int getDailyUsageLimit() {
        return DAILY_USAGE_LIMIT;
    }
}

