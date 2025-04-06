package com.practice.blog.post.dto.response;

import com.practice.blog.post.dto.summary.PostSummary;

import java.util.List;

public record PostListResponse(List<PostSummary> posts, Long totalPosts) {}

