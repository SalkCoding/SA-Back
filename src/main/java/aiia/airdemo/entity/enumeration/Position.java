package aiia.airdemo.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Position {

    Captain(0),       //기장
    Co_pilot(1),      //부기장
    Unit_Leader(2),   //운영진
    Official_Crew(3), //정규부원
    Semi_Crew(4);     //수습부원

    final private int level;

    @JsonCreator
    public static Position fromValue(int level) {
        for (Position value : Position.values()) {
            if (value.getLevel() == level) {
                return value;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + level + "]");
    }

    @Override
    public String toString() {
        return name(); // Enum 이름을 반환하도록 수정
    }
}
