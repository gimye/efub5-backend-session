package com.practice.blog.post.dto.response;

import com.practice.blog.post.domain.Post;

public record PostsResponse(Long postId, String nickName, String title, Long viewCount) {
    public static PostsResponse from(Post post) {
        return new PostsResponse(
                post.getId(),
                post.getWriter().getNickname(),
                post.getTitle(),
                post.getViewCount()
        );
    }
}
