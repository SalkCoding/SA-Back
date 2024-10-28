package com.aiiagcu.air.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Level {
    //기장
    Captain(0),

    //부기장
    Co_pilot(1),

    //운영진
    Unit_Leader(2),

    //정규부원
    Official_Crew(3),

    //수습부원
    Semi_Crew(4),


    //게스트 <- 동아리원이 아님
    Guest(99);

    final private int level;


    Level(int level){
        this.level = level;
    }
    public int getLevel() {
        return level;
    }



    @JsonCreator
    public static Level fromValue(@JsonProperty("level") int level) {
        for (Level value : Level.values()) {
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
