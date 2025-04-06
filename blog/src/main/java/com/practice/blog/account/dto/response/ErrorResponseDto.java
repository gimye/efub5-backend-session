package com.practice.blog.account.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDto {
    private String message;
    private int status;
}
