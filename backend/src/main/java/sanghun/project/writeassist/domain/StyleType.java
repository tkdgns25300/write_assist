package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StyleType {
    CONCISE_CLEAR("Concise/Clear"),
    EMOTIONAL_NATURAL("Emotional/Natural"),
    PROFESSIONAL_ACADEMIC("Professional/Academic");

    private final String description;
}
