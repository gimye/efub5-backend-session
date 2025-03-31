package com.practice.efubaccount.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDto {

    private String message;
    private int status;

}
