package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tone {
    VERY_CASUAL("1", "Very Casual"),
    CASUAL("2", "Casual"),
    STANDARD("3", "Standard"),
    FORMAL("4", "Formal"),
    VERY_FORMAL("5", "Very Formal");

    private final String value;
    private final String description;
}
