package com.practice.blog.comment.dto.request;

import com.practice.blog.account.entity.Account;
import com.practice.blog.comment.domain.Comment;
import com.practice.blog.post.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {
    private Long accountId;
    private String content;

    public Comment toEntity(Account account, Post post) {
        return Comment.builder()
                .content(content)
                .writer(account)
                .post(post)
                .build();
    }
}
