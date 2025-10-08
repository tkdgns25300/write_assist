package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StyleType {
    CONCISE_CLEAR("간결·명쾌"),
    EMOTIONAL_NATURAL("감성·자연스러움"),
    PROFESSIONAL_ACADEMIC("전문·학술적");

    private final String description;
}
