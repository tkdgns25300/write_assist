package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tone {
    VERY_CASUAL("1", "매우 캐주얼"),
    CASUAL("2", "캐주얼"),
    STANDARD("3", "표준"),
    FORMAL("4", "포멀"),
    VERY_FORMAL("5", "매우 포멀");

    private final String value;
    private final String description;
}

