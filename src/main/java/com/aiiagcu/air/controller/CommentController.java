package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.CommentDTO;
import com.aiiagcu.air.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 최상위 댓글 작성 엔드포인트
    @PostMapping("/posts/{postId}")
    public ResponseEntity<CommentDTO> writeTopLevelComment(@PathVariable String postId, @RequestBody CommentDTO commentDTO, @RequestParam String username) {
        CommentDTO createdComment = commentService.writeTopLevelComment(postId, commentDTO, username);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // 대댓글 작성 엔드포인트
    @PostMapping("/replies/{parentId}")
    public ResponseEntity<CommentDTO> writeReplyComment(@PathVariable Long parentId, @RequestBody CommentDTO commentDTO, @RequestParam String username) {
        CommentDTO createdComment = commentService.writeReplyComment(parentId, commentDTO, username);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    //댓글 수정
    @PatchMapping("/comment/{commentId}")
    public ResponseEntity<String> editComment(@PathVariable Long commentId,
                                              @RequestBody String newContent) {
        commentService.editComment(commentId, newContent);
        return ResponseEntity.ok("Comment edited successfully");
    }

    //id 를 이용하여 댓글 검색
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId) {
        // 이 Endpoint에 commentId로 요청한다면 사용자 정보가 노출됨
        // TODO: 접근 권한 인증 구현(본인 댓글 or 관리자)
        Optional<CommentDTO> comment = commentService.getCommentById(commentId);
        return comment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //모든 댓글 검색
    @GetMapping("/comment/all")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        // 이 Endpoint에 요청한다면 사용자 정보가 노출됨
        // TODO: 접근 권한 인증 구현(관리자)
        List<CommentDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

}
