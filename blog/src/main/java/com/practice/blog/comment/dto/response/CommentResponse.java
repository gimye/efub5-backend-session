package com.practice.blog.comment.dto.response;

import com.practice.blog.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {
    private Long commentId;
    private Long postId;
    private String writerNickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .postId(comment.getPost().getId())
                .writerNickname(comment.getWriter().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getModifiedAt())
                .build();
    }
}
