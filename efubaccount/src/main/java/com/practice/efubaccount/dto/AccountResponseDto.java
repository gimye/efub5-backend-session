package com.practice.efubaccount.dto;

import com.practice.efubaccount.entity.Account;
import lombok.Builder;
import lombok.Getter;

// Account 조회 후 응답 DTO

@Builder @Getter
public class AccountResponseDto {
    private String nickname;
    private String email;
    private String bio;

    public static AccountResponseDto from(Account account) {
        return AccountResponseDto.builder()
                .nickname(account.getNickname())
                .email(account.getEmail())
                .bio(account.getBio())
                .build();
    }
}