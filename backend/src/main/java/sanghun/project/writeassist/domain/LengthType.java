package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LengthType {
    SHORT("짧게"),
    STANDARD("표준"),
    LONG("길게");

    private final String description;
}
