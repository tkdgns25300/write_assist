package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Purpose {
    INFORMATION("Information Delivery"),
    PERSUASION_REQUEST("Persuasion/Request"),
    APOLOGY_REFUSAL("Apology/Refusal"),
    THANKS_PRAISE("Thanks/Praise"),
    FREEFORM("Freeform");

    private final String description;
}
