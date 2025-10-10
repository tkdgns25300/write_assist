package sanghun.project.writeassist.util;

import sanghun.project.writeassist.domain.StyleType;

/**
 * StyleType을 Vertex AI temperature 값으로 매핑하는 유틸리티 클래스
 */
public class TemperatureMapper {

    /**
     * StyleType에 따른 temperature 값을 반환합니다.
     *
     * Temperature 범위: 0.0 ~ 2.0
     * - 낮은 값(0.0~0.5): 일관적이고 예측 가능한 출력
     * - 중간 값(0.5~1.0): 균형잡힌 출력
     * - 높은 값(1.0~2.0): 창의적이고 다양한 출력
     *
     * @param styleType 스타일 타입
     * @return temperature 값
     */
    public static double getTemperature(StyleType styleType) {
        switch (styleType) {
            case CONCISE_CLEAR:
                // 간결·명쾌 → 일관적이고 직접적인 표현
                return 0.3;

            case EMOTIONAL_NATURAL:
                // 감성·자연스러움 → 창의적이고 다양한 감성 표현
                return 1.2;

            case PROFESSIONAL_ACADEMIC:
                // 전문·학술적 → 정확하고 형식적인 표현
                return 0.4;

            default:
                // 기본값
                return 0.7;
        }
    }
}

