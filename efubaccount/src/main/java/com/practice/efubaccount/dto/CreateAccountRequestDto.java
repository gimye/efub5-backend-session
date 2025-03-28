package com.practice.efubaccount.dto;

import com.practice.efubaccount.entity.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// 계정 생성 Request DTO
@Getter
public class CreateAccountRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    // Account 객체로 build
    public Account toEntity() {
        return Account.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}