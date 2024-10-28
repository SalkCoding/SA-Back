package com.aiiagcu.air.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// 엔띠띠가 아니라 그냥 클래스지만 걍 여기 넣었슴다
@Getter
@Setter
public class PostBlock {

    private int order;

    private PostBlockType type;

    private String content;

}
