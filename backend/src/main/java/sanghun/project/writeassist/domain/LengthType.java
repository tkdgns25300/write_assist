package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LengthType {
    SHORT("Short"),
    STANDARD("Standard"),
    LONG("Long");

    private final String description;
}
