package com.practice.blog.post.service;

import com.practice.blog.account.entity.Account;
import com.practice.blog.account.repository.AccountsRepository;
import com.practice.blog.global.exception.BlogException;
import com.practice.blog.global.exception.ExceptionCode;
import com.practice.blog.post.domain.Post;
import com.practice.blog.post.dto.request.PostCreateRequest;
import com.practice.blog.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
