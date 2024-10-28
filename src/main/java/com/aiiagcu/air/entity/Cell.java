package com.aiiagcu.air.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Cell {

    FLUTTER("flutter"),
    REACT("react"),
    SPRING("spring"),
    AI_RESEARCH("ai"),
    BRAND_DESIGN("branddesign"),
    UI_UX_DESIGN("uiuxdesign"),
    DESIGN("design");


    private final String cell;

    Cell(String cell) {
        this.cell = cell;
    }

    public String getCell() {
        return cell;
    }

    @JsonCreator
    public static Cell fromString(@JsonProperty("cell") String cell) {
        for (Cell value : Cell.values()) {
            if (value.getCell().equalsIgnoreCase(cell)) {
                return value;
            }
        }
        System.out.println("Received cell value: " + cell); // 디버깅 메시지 추가
        throw new IllegalArgumentException("No matching constant for [" + cell + "]");
    }

}
