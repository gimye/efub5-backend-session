package com.practice.blog.post.controller;

import com.practice.blog.post.domain.Post;
import com.practice.blog.post.dto.request.PostCreateRequest;
import com.practice.blog.post.dto.response.PostResponse;
import com.practice.blog.post.dto.response.PostsResponses;
import com.practice.blog.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시물 생성
    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@Valid @RequestBody PostCreateRequest request) {
        Long id = postService.createPost(request);
        return ResponseEntity.created(URI.create("/post/"+id)).build();
    }

    // 게시물 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<PostsResponses> readPost(){
        return ResponseEntity.ok(postService.readPosts());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> readPost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.readPost(id));
    }
}
