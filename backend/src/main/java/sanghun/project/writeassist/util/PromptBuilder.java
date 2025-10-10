package sanghun.project.writeassist.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sanghun.project.writeassist.domain.LengthType;
import sanghun.project.writeassist.domain.Purpose;
import sanghun.project.writeassist.domain.StyleType;
import sanghun.project.writeassist.domain.Tone;

@Slf4j
@Component
public class PromptBuilder {

    /**
     * 사용자 입력과 설정값을 기반으로 AI 프롬프트를 생성합니다.
     *
     * @param text       원본 텍스트
     * @param tone       톤 설정
     * @param purpose    목적 설정
     * @param lengthType 분량 설정
     * @param styleType  스타일 설정
     * @return 생성된 프롬프트
     */
    public String buildPrompt(String text, Tone tone, Purpose purpose, LengthType lengthType, StyleType styleType) {
        log.debug("Building prompt - Tone: {}, Purpose: {}, Length: {}, Style: {}",
            tone, purpose, lengthType, styleType);

        StringBuilder prompt = new StringBuilder();

        // 1. System Role 정의
        prompt.append("You are Write Assist AI, a professional writing assistant specialized in text correction and improvement.\n\n");

        // 2. 언어 감지 및 유지 (최우선 규칙)
        prompt.append("=== CRITICAL LANGUAGE RULE ===\n");
        prompt.append("1. Automatically detect the language of the input text\n");
        prompt.append("2. Respond ONLY in the EXACT SAME LANGUAGE as the input\n");
        prompt.append("3. Korean input → Korean output\n");
        prompt.append("4. English input → English output\n");
        prompt.append("5. Japanese input → Japanese output\n");
        prompt.append("6. NEVER translate or mix languages\n\n");

        // 3. 교정 요구사항
        prompt.append("=== CORRECTION REQUIREMENTS ===\n\n");

        // Tone
        prompt.append("1. TONE (Formality Level):\n");
        prompt.append("   ").append(buildToneInstruction(tone)).append("\n\n");

        // Purpose
        prompt.append("2. PURPOSE (Communication Goal):\n");
        prompt.append("   ").append(buildPurposeInstruction(purpose)).append("\n\n");

        // Length
        prompt.append("3. LENGTH (Text Amount):\n");
        prompt.append("   ").append(buildLengthInstruction(lengthType)).append("\n\n");

        // Style
        prompt.append("4. STYLE (Expression Method):\n");
        prompt.append("   ").append(buildStyleInstruction(styleType)).append("\n\n");

        // 4. 출력 규칙
        prompt.append("=== OUTPUT RULES ===\n");
        prompt.append("✓ Output ONLY the corrected text\n");
        prompt.append("✗ Do NOT include any explanations, notes, or meta-commentary\n");
        prompt.append("✗ Do NOT add phrases like 'Here is the corrected version:' or 'Corrected text:'\n");
        prompt.append("✗ Do NOT use quotation marks or formatting around the output\n");
        prompt.append("✗ Do NOT add numbering like '1.', '2.', or 'Version 1:'\n");
        prompt.append("✓ Just provide the final corrected text directly\n\n");

        // 5. 예시
        prompt.append("=== EXAMPLE ===\n");
        prompt.append("Input: \"오늘 회의 하실래요?\"\n\n");
        prompt.append("Correct Output:\n");
        prompt.append("오늘 회의하시겠습니까?\n\n");
        prompt.append("Wrong Outputs:\n");
        prompt.append("Version 1: 오늘 회의하시겠습니까? ✗\n");
        prompt.append("\"오늘 회의하시겠습니까?\" ✗\n");
        prompt.append("Here is the correction: 오늘 회의하시겠습니까? ✗\n\n");

        // 6. 원본 텍스트
        prompt.append("========================================\n");
        prompt.append("ORIGINAL TEXT TO CORRECT:\n");
        prompt.append("========================================\n\n");
        prompt.append(text);
        prompt.append("\n\n========================================");

        String finalPrompt = prompt.toString();
        log.debug("Generated prompt length: {} characters", finalPrompt.length());

        return finalPrompt;
    }

    /**
     * 톤 설정에 따른 지시사항 생성
     */
    private String buildToneInstruction(Tone tone) {
        switch (tone) {
            case VERY_CASUAL:
                return "Use very casual and friendly language. Use informal expressions and friendly tone as if talking to a close friend.";
            case CASUAL:
                return "Use casual and approachable language. Maintain a friendly but slightly professional tone.";
            case STANDARD:
                return "Use standard professional language. Balance between formal and friendly, appropriate for general business communication.";
            case FORMAL:
                return "Use formal and respectful language. Maintain professional courtesy suitable for external partners or superiors.";
            case VERY_FORMAL:
                return "Use highly formal and respectful language. Show maximum courtesy and professionalism for important stakeholders.";
            default:
                return "Use standard language.";
        }
    }

    /**
     * 목적 설정에 따른 지시사항 생성
     */
    private String buildPurposeInstruction(Purpose purpose) {
        switch (purpose) {
            case INFORMATION:
                return "Focus on clear and accurate information delivery. Organize content logically and ensure easy comprehension.";
            case PERSUASION_REQUEST:
                return "Craft persuasive and compelling language for making requests. Be convincing yet respectful and considerate.";
            case APOLOGY_REFUSAL:
                return "Express sincere apology or polite refusal with empathy. Show genuine understanding and offer alternatives if possible.";
            case THANKS_PRAISE:
                return "Express genuine gratitude or sincere praise. Be warm, appreciative, and specific about what you're thankful for.";
            case FREEFORM:
                return "Maintain the original intent and message while improving overall clarity, flow, and naturalness.";
            default:
                return "Improve the text while maintaining its original purpose.";
        }
    }

    /**
     * 분량 설정에 따른 지시사항 생성
     */
    private String buildLengthInstruction(LengthType lengthType) {
        switch (lengthType) {
            case SHORT:
                return "Make the text concise and brief. Summarize key points only and remove unnecessary details or redundancy.";
            case STANDARD:
                return "Maintain similar length to the original text. Improve clarity and expression without significant expansion or reduction.";
            case LONG:
                return "Expand the text with more details, context, and explanations. Add supporting information and elaborate where appropriate.";
            default:
                return "Maintain appropriate length.";
        }
    }

    /**
     * 스타일 설정에 따른 지시사항 생성
     */
    private String buildStyleInstruction(StyleType styleType) {
        switch (styleType) {
            case CONCISE_CLEAR:
                return "Use concise and clear language. Be direct, straightforward, and avoid flowery or decorative expressions.";
            case EMOTIONAL_NATURAL:
                return "Use natural and emotionally resonant language. Make it feel warm, human, and relatable with appropriate emotional nuance.";
            case PROFESSIONAL_ACADEMIC:
                return "Use professional and academic language. Employ formal vocabulary, structured expression, and objective tone.";
            default:
                return "Use clear and appropriate language.";
        }
    }
}

