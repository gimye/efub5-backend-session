package com.practice.blog.comment.service;

import com.practice.blog.account.dto.response.AccountCommentResponse;
import com.practice.blog.account.entity.Account;
import com.practice.blog.account.repository.AccountsRepository;
import com.practice.blog.comment.domain.Comment;
import com.practice.blog.comment.dto.request.CommentRequest;
import com.practice.blog.comment.repository.CommentRepository;
import com.practice.blog.global.exception.BlogException;
import com.practice.blog.global.exception.ExceptionCode;
import com.practice.blog.post.domain.Post;
import com.practice.blog.post.dto.response.PostCommentResponse;
import com.practice.blog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final AccountsRepository accountsRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Long postId, CommentRequest commentRequest) {
        Long accountId = commentRequest.getAccountId();
        Account writer = findByAccountId(accountId);
        Post post = findByPostId(postId);
        Comment newComment = commentRequest.toEntity(writer, post);
        commentRepository.save(newComment);
        return newComment.getId();
    }

    @Transactional(readOnly=true)
    public PostCommentResponse getPostCommentList(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostIdOrderByCreatedAt(postId);
        return PostCommentResponse.of(postId,commentList);
    }

    @Transactional(readOnly=true)
    public AccountCommentResponse getAccountCommentList(Long accountId) {
        Account account = findByAccountId(accountId);
        List<Comment> commentList = commentRepository.findAllByWriterAccountIdOrderByCreatedAtDesc(accountId);
        return AccountCommentResponse.of(account, commentList);
    }

    private Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()-> new BlogException(ExceptionCode.POST_NOT_FOUND));
    }

    private Account findByAccountId(Long accountId) {
        return accountsRepository.findByAccountId(accountId)
                .orElseThrow(()-> new BlogException(ExceptionCode.ACCOUNT_NOT_FOUND));
    }
}
