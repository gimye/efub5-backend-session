package com.practice.blog.comment.service;

import com.practice.blog.account.dto.response.AccountCommentResponse;
import com.practice.blog.account.entity.Account;
import com.practice.blog.account.repository.AccountsRepository;
import com.practice.blog.account.service.AccountsService;
import com.practice.blog.comment.domain.Comment;
import com.practice.blog.comment.dto.request.CommentRequest;
import com.practice.blog.comment.repository.CommentRepository;
import com.practice.blog.post.domain.Post;
import com.practice.blog.post.dto.response.PostCommentResponse;
import com.practice.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final AccountsService accountsService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Long postId, CommentRequest commentRequest) {
        Long accountId = commentRequest.getAccountId();
        Account writer = accountsService.findByAccountId(accountId);
        Post post = postService.findByPostId(postId);
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
        Account account = accountsService.findByAccountId(accountId);
        List<Comment> commentList = commentRepository.findAllByWriterAccountIdOrderByCreatedAtDesc(accountId);
        return AccountCommentResponse.of(account, commentList);
    }
}
