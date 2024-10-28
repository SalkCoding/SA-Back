package com.aiiagcu.air.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false, nullable = false)
    @JsonIgnore
    private Long id;

    //댓글 입장에서는 게시글과 사용자는 다대일 관계이기 때문
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn
    private Post post;

    private String content;

    @ColumnDefault("FALSE")
    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();


    // 대댓글 리스트 반환 메서드 (필요시)
    @Getter
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();

    public void updateParent(Comment comment) {
        this.parent = comment;
    }

    // 대댓글 추가 메서드
    public void addReply(Comment reply) {
        replies.add(reply);
        reply.updateParent(this);
    }

}
