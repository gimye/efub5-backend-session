package com.practice.blog.post.service;

import com.practice.blog.account.entity.Account;
import com.practice.blog.account.repository.AccountsRepository;
import com.practice.blog.global.exception.BlogException;
import com.practice.blog.global.exception.ExceptionCode;
import com.practice.blog.post.domain.Post;
import com.practice.blog.post.dto.request.PostCreateRequest;
import com.practice.blog.post.dto.request.PostUpdateRequest;
import com.practice.blog.post.dto.response.PostResponse;
import com.practice.blog.post.dto.response.PostsResponse;
import com.practice.blog.post.dto.response.PostsResponses;
import com.practice.blog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AccountsRepository accountsRepository;

    @Transactional
    public Long createPost(PostCreateRequest postCreateRequest) {
        Long accountId = postCreateRequest.accountId();
        Account writerAccount = accountsRepository.findByAccountId(accountId)
                .orElseThrow(()-> new BlogException(ExceptionCode.ACCOUNT_NOT_FOUND));
        Post newPost = postCreateRequest.toEntity(writerAccount);
        postRepository.save(newPost);
        return newPost.getId();
    }

    @Transactional
    public PostResponse readPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new BlogException(ExceptionCode.POST_NOT_FOUND));
        post.increaseViewCount();
        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public PostsResponses readPosts() {
        List<PostsResponse> postsResponses = postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostsResponse::from).toList();
        return new PostsResponses(postsResponses);
    }

    @Transactional
    public void updatePostContent(Long postId, PostUpdateRequest request, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new BlogException(ExceptionCode.POST_NOT_FOUND));
        if(!post.getWriter().getPassword().equals(password)) {
            throw new BlogException(ExceptionCode.POST_ACCOUNT_MISMATCH);
        }
        post.changeContent(request.content());
    }

    @Transactional
    public void deletePost(Long postId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new BlogException(ExceptionCode.POST_NOT_FOUND));
        if(!post.getWriter().getPassword().equals(password)) {
            throw new BlogException(ExceptionCode.POST_ACCOUNT_MISMATCH);
        }
        postRepository.delete(post);
    }

}
