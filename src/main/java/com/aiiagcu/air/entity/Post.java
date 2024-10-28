package com.aiiagcu.air.entity;

import com.aiiagcu.air.dto.PostInput;
import com.aiiagcu.air.util.Base62;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import io.hypersistence.utils.hibernate.id.Tsid;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post extends BaseEntity {

    @Id
    @Tsid
    @Column(name = "post_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn//(nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Lob
    @Type(JsonType.class)
    @Column(columnDefinition = "TEXT")
    private List<PostBlock> blocks = new ArrayList<>();


    //댓글 정렬
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")//댓글 정렬
    private List<Comment> comments = new ArrayList<>();


    public void addComment(Comment newComment) {
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.add(newComment);
        //댓글에 속한 게시물을 설정하는 코드
        //comments.set(this);

    }

    public static Post toSaveEntity(PostInput source) {
        Post saveEntity = new Post();

        if (source.getId() != null) {
            saveEntity.setId(Base62.fromBase62(source.getId()));
        }
        saveEntity.setAuthor(source.getAuthor());
        saveEntity.setTitle(source.getTitle());
        saveEntity.setBlocks(source.getBlocks());

        return saveEntity;
    }

}
