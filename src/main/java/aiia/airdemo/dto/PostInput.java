package aiia.airdemo.dto;

import aiia.airdemo.entity.PostBlock;
import aiia.airdemo.entity.User;
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
import java.util.List;

/**
 * DTO for {@link aiia.airdemo.entity.Post}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostInput implements Serializable {

    private String id;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @NotNull
    private User author;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 100)
    private String title;

    private List<PostBlock> blocks;

}
