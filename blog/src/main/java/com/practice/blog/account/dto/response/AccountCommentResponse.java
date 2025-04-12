package com.practice.blog.account.dto.response;

import com.practice.blog.account.entity.Account;
import com.practice.blog.comment.domain.Comment;
import com.practice.blog.comment.dto.response.CommentResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountCommentResponse {
    private String writerNickname;
    private List<CommentResponse> accountCommentList;
    private Long count;

    public static AccountCommentResponse of(Account account, List<Comment> commentList) {
        return AccountCommentResponse.builder()
                .writerNickname(account.getNickname())
                .accountCommentList(commentList.stream().map(CommentResponse::of).collect(Collectors.toList()))
                .count((long) commentList.size())
                .build();
    }
}
