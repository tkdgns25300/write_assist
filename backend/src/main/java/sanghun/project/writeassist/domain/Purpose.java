package sanghun.project.writeassist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Purpose {
    INFORMATION("정보 전달"),
    PERSUASION_REQUEST("설득·요청"),
    APOLOGY_REFUSAL("사과·거절"),
    THANKS_PRAISE("감사·칭찬"),
    FREEFORM("자유");

    private final String description;
}
