package com.aiiagcu.air.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostList {
    private Long count;
    private List<PostListItem> posts;
}