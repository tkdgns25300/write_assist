package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LengthType {
    SHORT("Short", "짧게"),
    STANDARD("Standard", "표준"),
    LONG("Long", "길게");

    private final String value;
    private final String description;
}

