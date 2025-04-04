package com.practice.blog.global.exception;

public enum ClientExceptionCode {
    // 전체
    INTERNAL_SERVER_ERROR,
    INVALID_PARAMETER,

    // Account
    ACCOUNT_NOT_FOUND,

    // Post
    POST_NOT_FOUND,
    POST_CONTENT_INVALID_LENGTH,
    POST_ACCOUNT_MISMATCH
}
