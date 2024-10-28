package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.CommentDTO;
import com.aiiagcu.air.dto.CommentOutputDTO;
import com.aiiagcu.air.entity.Comment;
import com.aiiagcu.air.entity.Post;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.repository.CommentRepository;
import com.aiiagcu.air.repository.PostRepository;
import com.aiiagcu.air.repository.UserRepository;
import com.aiiagcu.air.util.Base62;
import com.aiiagcu.air.util.SHA256;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    @Transactional
    public CommentDTO writeTopLevelComment(String postId, CommentDTO commentDTO, String username) {
        // 게시물(Post)과 작성자(User)를 조회합니다.
        Optional<Post> optionalPost = postRepository.findById(Base62.fromBase62(postId));
        Optional<User> optionalUser = userRepository.findByStudentNumber(username);

        // 게시물이 존재하지 않을 경우 예외를 던집니다.
        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("게시물을 찾을 수 없습니다. postId: " + postId);
        }

        // 사용자가 존재하지 않을 경우 예외를 던집니다.
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. username: " + username);
        }

        // 조회된 게시물과 사용자를 변수에 할당합니다.
        Post post = optionalPost.get();
        User user = optionalUser.get();

        // 새로운 댓글을 생성하고 필요한 정보를 설정합니다.
        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setContent(commentDTO.getContent());
        newComment.setPost(post);

        // 댓글을 게시물에 추가합니다.
        post.addComment(newComment);

        // 댓글을 저장합니다.
        Comment savedComment = commentRepository.save(newComment);

        // 저장된 댓글을 DTO로 변환하여 반환합니다.
        return convertToDTO(savedComment);
    }

    @Transactional
    public CommentDTO writeReplyComment(long parentId, CommentDTO commentDTO, String username) {
        // 부모 댓글과 작성자(User)를 조회합니다.
        Optional<Comment> optionalParentComment = commentRepository.findById(parentId);
        Optional<User> optionalUser = userRepository.findByStudentNumber(username);

        // 부모 댓글이 존재하지 않을 경우 예외를 던집니다.
        if (optionalParentComment.isEmpty()) {
            throw new IllegalArgumentException("부모 댓글을 찾을 수 없습니다. parentId: " + parentId);
        }

        // 사용자가 존재하지 않을 경우 예외를 던집니다.
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다. username: " + username);
        }

        // 조회된 부모 댓글과 사용자를 변수에 할당합니다.
        Comment parentComment = optionalParentComment.get();
        User user = optionalUser.get();

        // 부모 댓글이 속한 게시물을 가져옵니다.
        Post post = parentComment.getPost();

        // 새로운 대댓글을 생성하고 필요한 정보를 설정합니다.
        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setContent(commentDTO.getContent());
        newComment.setPost(post);

        // 대댓글을 부모 댓글에 추가합니다.
        parentComment.addReply(newComment);

        // 대댓글을 저장합니다.
        Comment savedComment = commentRepository.save(newComment);

        // 저장된 대댓글을 DTO로 변환하여 반환합니다.
        return convertToDTO(savedComment);
    }





    // 댓글 수정기능
    @Transactional
    public void editComment(Long commentId, String newContent) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(newContent);
        } else {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        }
    }



    //댓글의 id를 기반으로 댓글을 검색합니다.
    public Optional<CommentDTO> getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.map(this::convertToDTO);
    }



    //모든 댓글을 검색합니다.
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // 댓글의 id를 기반으로 댓글을 삭제합니다.
    @Transactional
    public void deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        }
    }


    //CommentDTO 를 Comment 엔티티로 변환하는 메서드
    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setUser(commentDTO.getUser());
        comment.setContent(commentDTO.getContent());
        return comment;
    }



    //Comment 엔티티를 CommentDTO로 변환하는 메서드
    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUser(comment.getUser());
        commentDTO.setContent(comment.getContent());
        return commentDTO;
    }

    //Comment 엔티티를 CommentOutputDTO로 변환하는 메서드
    public static CommentOutputDTO convertToOutputDTO(Comment comment) {
        final String postId = Long.toString(comment.getPost().getId());
        final String authorId = Long.toString(comment.getUser().getId());

        CommentOutputDTO commentOutputDTO = new CommentOutputDTO();
        commentOutputDTO.setId(comment.getId());
        commentOutputDTO.setAuthor(anonymize(postId, authorId));
        commentOutputDTO.setContent(comment.getContent());
        return commentOutputDTO;
    }

    // postID + authorID를 해시하여 익명화하는 메서드
    private static String anonymize(String postId, String authorId) {
        final String ciphertext = SHA256.encrypt(postId+authorId);
        return ciphertext.substring(0, 6);
    }

}
