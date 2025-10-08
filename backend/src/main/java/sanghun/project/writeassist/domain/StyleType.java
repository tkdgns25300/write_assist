package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StyleType {
    CONCISE_CLEAR("Concise/Clear", "간결·명쾌"),
    EMOTIONAL_NATURAL("Emotional/Natural", "감성·자연스러움"),
    PROFESSIONAL_ACADEMIC("Professional/Academic", "전문·학술적");

    private final String value;
    private final String description;
}

