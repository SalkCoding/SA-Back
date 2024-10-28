package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.PostInput;
import com.aiiagcu.air.dto.PostList;
import com.aiiagcu.air.dto.PostOutput;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PostController {

    private PostService postService;

    // 작성글 목록 조회
    @GetMapping("/post/list/{pageNumber}")
    public ResponseEntity<PostList> listPost(@PathVariable("pageNumber") int pageNumber) {
        return ResponseEntity.ok(postService.getPostList(pageNumber));
    }

    // 글 본문 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<PostOutput> updatePost(@PathVariable("id") String id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    // 새로운 글 작성
    @PostMapping("/post/create")
    public ResponseEntity<PostInput> createPost(@RequestBody PostInput postInput, @SessionAttribute(name = "loginUser", required = false) User user) {
        postInput.setAuthor(user);
        PostInput save = postService.save(postInput);

        return save.getId() == null ?
                ResponseEntity.internalServerError().build():
                ResponseEntity.ok(save);
    }

    // ID로 글 수정
    @PatchMapping("/post/{id}")
    public ResponseEntity<PostInput> updatePost(@RequestBody PostInput postInput, @PathVariable("id") String id, @SessionAttribute(name = "loginUser", required = false) User user) {
        PostInput save = postService.update(postInput, id, user);

        return ResponseEntity.ok(save);
    }

}
