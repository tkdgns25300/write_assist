package sanghun.project.writeassist.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PromptBuilder 테스트")
class PromptBuilderTest {

    private PromptBuilder promptBuilder;

    @BeforeEach
    void setUp() {
        promptBuilder = new PromptBuilder();
    }

    @Test
    @DisplayName("기본 프롬프트 구조가 올바르게 생성된다")
    void buildPrompt_BasicStructure() {
        // given
        String text = "Hello, this is test text.";

        // when
        String prompt = promptBuilder.buildPrompt(
            text,
            Tone.STANDARD,
            Purpose.INFORMATION,
            LengthType.STANDARD,
            StyleType.CONCISE_CLEAR
        );

        // then
        assertThat(prompt).contains("Write Assist AI");
        assertThat(prompt).contains("CRITICAL LANGUAGE RULE");
        assertThat(prompt).contains("EXACT SAME LANGUAGE");
        assertThat(prompt).contains("CORRECTION REQUIREMENTS");
        assertThat(prompt).contains("1. TONE");
        assertThat(prompt).contains("2. PURPOSE");
        assertThat(prompt).contains("3. LENGTH");
        assertThat(prompt).contains("4. STYLE");
        assertThat(prompt).contains("OUTPUT RULES");
        assertThat(prompt).contains("EXAMPLE");
        assertThat(prompt).contains("ORIGINAL TEXT TO CORRECT:");
        assertThat(prompt).contains(text);
    }

    @Test
    @DisplayName("VERY_CASUAL 톤 설정이 프롬프트에 반영된다")
    void buildPrompt_VeryCasualTone() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.VERY_CASUAL, Purpose.FREEFORM, LengthType.STANDARD, StyleType.EMOTIONAL_NATURAL
        );

        // then
        assertThat(prompt).contains("very casual and friendly");
        assertThat(prompt).contains("informal expressions");
    }

    @Test
    @DisplayName("VERY_FORMAL 톤 설정이 프롬프트에 반영된다")
    void buildPrompt_VeryFormalTone() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.VERY_FORMAL, Purpose.PERSUASION_REQUEST, LengthType.LONG, StyleType.PROFESSIONAL_ACADEMIC
        );

        // then
        assertThat(prompt).contains("highly formal and respectful");
        assertThat(prompt).contains("maximum courtesy");
    }

    @Test
    @DisplayName("PERSUASION_REQUEST 목적 설정이 프롬프트에 반영된다")
    void buildPrompt_PersuasionPurpose() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.FORMAL, Purpose.PERSUASION_REQUEST, LengthType.STANDARD, StyleType.CONCISE_CLEAR
        );

        // then
        assertThat(prompt).contains("persuasive and compelling");
        assertThat(prompt).contains("convincing yet respectful");
    }

    @Test
    @DisplayName("SHORT 분량 설정이 프롬프트에 반영된다")
    void buildPrompt_ShortLength() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.STANDARD, Purpose.INFORMATION, LengthType.SHORT, StyleType.CONCISE_CLEAR
        );

        // then
        assertThat(prompt).contains("concise and brief");
        assertThat(prompt).contains("Summarize");
    }

    @Test
    @DisplayName("LONG 분량 설정이 프롬프트에 반영된다")
    void buildPrompt_LongLength() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.STANDARD, Purpose.INFORMATION, LengthType.LONG, StyleType.PROFESSIONAL_ACADEMIC
        );

        // then
        assertThat(prompt).contains("Expand the text");
        assertThat(prompt).contains("more details");
    }

    @Test
    @DisplayName("PROFESSIONAL_ACADEMIC 스타일 설정이 프롬프트에 반영된다")
    void buildPrompt_ProfessionalStyle() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.FORMAL, Purpose.INFORMATION, LengthType.STANDARD, StyleType.PROFESSIONAL_ACADEMIC
        );

        // then
        assertThat(prompt).contains("professional and academic");
        assertThat(prompt).contains("formal vocabulary");
    }

    @Test
    @DisplayName("한글 텍스트 입력 시 언어 유지 지시사항이 포함된다")
    void buildPrompt_KoreanText() {
        // given
        String koreanText = "안녕하세요. 오늘 날씨가 좋네요.";

        // when
        String prompt = promptBuilder.buildPrompt(
            koreanText, Tone.STANDARD, Purpose.INFORMATION, LengthType.STANDARD, StyleType.CONCISE_CLEAR
        );

        // then
        assertThat(prompt).contains("CRITICAL LANGUAGE RULE");
        assertThat(prompt).contains("Automatically detect the language");
        assertThat(prompt).contains("EXACT SAME LANGUAGE");
        assertThat(prompt).contains("Korean input → Korean output");
        assertThat(prompt).contains("NEVER translate");
        assertThat(prompt).contains(koreanText);
    }

    @Test
    @DisplayName("복합 설정 시나리오 - 표준 업무 메일")
    void buildPrompt_StandardBusinessEmail() {
        // given
        String text = "회의 일정을 조율하고 싶습니다.";

        // when
        String prompt = promptBuilder.buildPrompt(
            text, Tone.FORMAL, Purpose.INFORMATION, LengthType.STANDARD, StyleType.CONCISE_CLEAR
        );

        // then
        assertThat(prompt).contains("formal and respectful");
        assertThat(prompt).contains("information delivery");
        assertThat(prompt).contains("similar length");
        assertThat(prompt).contains("concise and clear");
        assertThat(prompt).contains(text);
    }

    @Test
    @DisplayName("복합 설정 시나리오 - 친한 동료 대화")
    void buildPrompt_CasualChat() {
        // given
        String text = "오늘 점심 뭐 먹을까?";

        // when
        String prompt = promptBuilder.buildPrompt(
            text, Tone.VERY_CASUAL, Purpose.FREEFORM, LengthType.STANDARD, StyleType.EMOTIONAL_NATURAL
        );

        // then
        assertThat(prompt).contains("very casual");
        assertThat(prompt).contains("Maintain the original intent");
        assertThat(prompt).contains("natural and emotionally");
        assertThat(prompt).contains(text);
    }

    @Test
    @DisplayName("프롬프트에 불필요한 설명 금지 지시가 포함된다")
    void buildPrompt_NoExplanation() {
        // when
        String prompt = promptBuilder.buildPrompt(
            "test", Tone.STANDARD, Purpose.INFORMATION, LengthType.STANDARD, StyleType.CONCISE_CLEAR
        );

        // then
        assertThat(prompt).contains("OUTPUT RULES");
        assertThat(prompt).contains("Do NOT include explanations");
        assertThat(prompt).contains("Output ONLY the corrected text");
        assertThat(prompt).contains("Just provide the final corrected text directly");
    }
}

