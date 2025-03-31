package com.practice.efubaccount.controller;

import com.practice.efubaccount.dto.AccountResponseDto;
import com.practice.efubaccount.dto.CreateAccountResponseDto;
import com.practice.efubaccount.dto.BioUpdateRequestDto;
import com.practice.efubaccount.dto.CreateAccountRequestDto;
import com.practice.efubaccount.service.AccountsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

//    private final AccountsService accountsService;
//
//    // 회원 조회: GET /accounts/{accountId}
//    @GetMapping("/{accountId}")
//    public ResponseEntity<AccountResponseDto> getAccount() {
//    }
//
//    // 계정 생성 POST /accounts
//    @PostMapping
//    public ResponseEntity<CreateAccountResponseDto> createAccount() {
//    }
//
//    // 계정 프로필(자기소개) 수정: PATCH /accounts/profile/{accountId}
//    @PatchMapping("/profile/{accountId}")
//    public ResponseEntity<AccountResponseDto> updateAccount() {
//    }
//
//    // 계정 논리적 삭제(탈퇴): PATCH /accounts/{accountId}
//    @PatchMapping("/{accountId}")
//    public ResponseEntity<String> deleteAccount() {
//    }
//
//    // 계정 물리적 삭제: DELETE /accounts/{accountId}
//    @DeleteMapping("/{accountId}")
//    public ResponseEntity<String> physicalDeleteAccount() {
//    }
}
