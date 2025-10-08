package sanghun.project.writeassist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "faq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Faq(String question, String answer, Integer sortOrder, Boolean isActive) {
        this.question = question;
        this.answer = answer;
        this.sortOrder = sortOrder != null ? sortOrder : 0;
        this.isActive = isActive != null ? isActive : true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * FAQ를 활성화합니다.
     */
    public void activate() {
        this.isActive = true;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * FAQ를 비활성화합니다.
     */
    public void deactivate() {
        this.isActive = false;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * FAQ의 내용을 수정합니다.
     */
    public void updateContent(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * FAQ의 정렬 순서를 변경합니다.
     */
    public void updateSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        this.updatedAt = LocalDateTime.now();
    }
}

