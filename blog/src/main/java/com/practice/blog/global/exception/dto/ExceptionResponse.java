package com.practice.blog.global.exception.dto;

public record ExceptionResponse(String httpMethod, String path, String code, String message) {
}
