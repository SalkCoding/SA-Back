package com.aiiagcu.air.dto;

import com.aiiagcu.air.entity.Comment;
import com.aiiagcu.air.entity.Post;
import com.aiiagcu.air.entity.PostBlock;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.util.Base62;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.aiiagcu.air.service.CommentService.convertToOutputDTO;

/**
 * DTO for {@link com.aiiagcu.air.entity.Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostInput implements Serializable {

    private String id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    // @NotNull
    private User author;
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 100)
    private String title;
    private List<PostBlock> blocks;
    private List<CommentOutputDTO> comments;

    public static PostInput toSaveDTO(Post source) {
        PostInput saveDTO = new PostInput();

        saveDTO.setId(Base62.toBase62(source.getId()));
        saveDTO.setCreatedTime(source.getCreatedTime());
        saveDTO.setUpdatedTime(source.getUpdatedTime());
        saveDTO.setAuthor(source.getAuthor());
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
