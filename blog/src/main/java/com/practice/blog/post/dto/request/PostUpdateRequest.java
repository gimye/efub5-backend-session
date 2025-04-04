package com.practice.blog.post.dto.request;

import jakarta.validation.constraints.Size;

public record PostUpdateRequest(@Size(min=5, max=500, message = "내용은 5자이상 500자이하로 입력해야합니다.") String content) {
}
