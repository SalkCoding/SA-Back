package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Post;
import com.aiiagcu.air.util.Base62;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostListItem {
    private String id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String authorName;
    private String title;

    public static PostListItem toSaveDTO(Post source) {
        PostListItem saveDTO = new PostListItem();

        saveDTO.setId(Base62.toBase62(source.getId()));
        saveDTO.setCreatedTime(source.getCreatedTime());
        saveDTO.setUpdatedTime(source.getUpdatedTime());
        saveDTO.setAuthorName(source.getAuthor().getName());
        saveDTO.setTitle(source.getTitle());

        return saveDTO;
    }
}
