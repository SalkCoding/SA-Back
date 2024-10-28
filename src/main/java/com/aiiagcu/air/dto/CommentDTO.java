package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    private User user;

    private String content;

    private Post post;

    private Long parentId;

}
