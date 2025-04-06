package com.practice.blog.post.dto.summary;

import com.practice.blog.post.domain.Post;

// PostListResponse 내부 요소로 사용

public record PostSummary(Long postId, String nickName, String title, Long viewCount) {
    public static PostSummary from(Post post) {
        return new PostSummary(
                post.getId(),
                post.getWriter().getNickname(),
                post.getTitle(),
                post.getViewCount()
        );
    }
}
