package com.practice.blog.post.dto.response;

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
public class PostCommentResponse {
    private Long postId;
    private List<CommentResponse> postCommentList;
    private Long count;

    public static PostCommentResponse of(Long postId, List<Comment> commentList) {
        return PostCommentResponse.builder()
                .postId(postId)
                .postCommentList(commentList.stream().map(CommentResponse::of).collect(Collectors.toList()))
                .count((long) commentList.size())
                .build();

    }
}
