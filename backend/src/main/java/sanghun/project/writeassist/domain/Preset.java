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
@Table(name = "preset")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Preset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "tone", nullable = false)
    private Integer tone;

    @Column(name = "purpose", nullable = false, length = 50)
    private String purpose;

    @Column(name = "length_type", nullable = false, length = 20)
    private String lengthType;

    @Column(name = "style_type", nullable = false, length = 30)
    private String styleType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Preset(String name, String description, Integer tone, String purpose, String lengthType, String styleType) {
        this.name = name;
        this.description = description;
        this.tone = tone;
        this.purpose = purpose;
        this.lengthType = lengthType;
        this.styleType = styleType;
        this.createdAt = LocalDateTime.now();
    }
}

