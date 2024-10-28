package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Comment;
import com.aiiagcu.air.entity.Post;
import com.aiiagcu.air.entity.PostBlock;
import com.aiiagcu.air.util.Base62;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.aiiagcu.air.service.CommentService.convertToOutputDTO;

@Data
public class PostOutput implements Serializable {

    private String id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String authorName;
    private String title;
    private List<PostBlock> blocks;
    private List<CommentOutputDTO> comments;

    public static PostOutput toSaveDTO(Post source) {
        PostOutput saveDTO = new PostOutput();

        saveDTO.setId(Base62.toBase62(source.getId()));
        saveDTO.setCreatedTime(source.getCreatedTime());
        saveDTO.setUpdatedTime(source.getUpdatedTime());
        saveDTO.setAuthorName(source.getAuthor().getName());
        saveDTO.setTitle(source.getTitle());
        saveDTO.setBlocks(source.getBlocks());

        List<CommentOutputDTO> comments = new ArrayList<>();
        for(Comment comment: source.getComments()) {
            comments.add(convertToOutputDTO(comment));
        }
        saveDTO.setComments(comments);

        return saveDTO;
    }

}
