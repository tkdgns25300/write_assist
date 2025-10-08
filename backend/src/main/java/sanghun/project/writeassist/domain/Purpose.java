package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Purpose {
    INFORMATION("Information", "정보 전달"),
    PERSUASION_REQUEST("Persuasion/Request", "설득·요청"),
    APOLOGY_REFUSAL("Apology/Refusal", "사과·거절"),
    THANKS_PRAISE("Thanks/Praise", "감사·칭찬"),
    FREEFORM("Freeform", "자유");

    private final String value;
    private final String description;
}

