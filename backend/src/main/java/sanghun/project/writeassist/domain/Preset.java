package sanghun.project.writeassist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "tone", nullable = false)
    private Tone tone;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "length_type", nullable = false)
    private LengthType lengthType;

    @Enumerated(EnumType.STRING)
    @Column(name = "style_type", nullable = false)
    private StyleType styleType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Preset(String name, String description, Tone tone, Purpose purpose, LengthType lengthType, StyleType styleType) {
        this.name = name;
        this.description = description;
        this.tone = tone;
        this.purpose = purpose;
        this.lengthType = lengthType;
        this.styleType = styleType;
        this.createdAt = LocalDateTime.now();
    }
}

