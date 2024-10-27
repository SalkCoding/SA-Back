package aiia.airdemo.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Unit {

    FLUTTER("flutter"),
    REACT("react"),
    SPRING("spring"),
    AI_RESEARCH("ai"),
    BRAND_DESIGN("branddesign"),
    UI_UX_DESIGN("uiuxdesign"),
    DESIGN("design");

    private final String unit;

    @JsonCreator
    public static Unit fromString(String unit) {
        for (Unit value : Unit.values()) {
            if (value.getUnit().equalsIgnoreCase(unit)) {
                return value;
            }
        }
        System.out.println("Received unit value: " + unit); // 디버깅 메시지 추가
        throw new IllegalArgumentException("No matching constant for [" + unit + "]");
    }
}
