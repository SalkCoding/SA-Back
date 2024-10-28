package com.aiiagcu.air.entity;

public enum PostBlockType {

    Text("Text"),

    Image("Image");


    final private String type;


    PostBlockType(String type){
        this.type = type;
    }

}
