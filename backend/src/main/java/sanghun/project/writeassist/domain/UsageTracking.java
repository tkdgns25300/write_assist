package sanghun.project.writeassist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "usage_tracking",
    uniqueConstraints = @UniqueConstraint(
        name = "unique_usage_tracking",
        columnNames = {"user_uuid", "user_agent", "usage_date"}
    )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsageTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", nullable = false, length = 36)
    private String userUuid;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "usage_date", nullable = false)
    private LocalDate usageDate;

    @Column(name = "usage_count", nullable = false)
    private Integer usageCount;

    @Column(name = "last_used_at", nullable = false)
    private LocalDateTime lastUsedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public UsageTracking(String userUuid, String userAgent, LocalDate usageDate) {
        this.userUuid = userUuid;
        this.userAgent = userAgent;
        this.usageDate = usageDate;
        this.usageCount = 0;
        this.lastUsedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    /**
     * 사용 횟수를 1 증가시킵니다.
     */
    public void incrementUsageCount() {
        this.usageCount++;
        this.lastUsedAt = LocalDateTime.now();
    }

    /**
     * 사용 횟수를 초기화합니다.
     */
    public void resetUsageCount() {
        this.usageCount = 0;
        this.lastUsedAt = LocalDateTime.now();
    }
}

