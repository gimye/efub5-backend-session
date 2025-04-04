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

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountsService accountsService;

    // 회원 조회: GET /accounts/{accountId}
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable("accountId") Long accountId) {
        AccountResponseDto responseDto = accountsService.getAccount(accountId);
        return ResponseEntity.ok(responseDto);
    }

    // 계정 생성 POST /accounts
    @PostMapping
    public ResponseEntity<CreateAccountResponseDto> createAccount(@RequestBody @Valid CreateAccountRequestDto requestDto) {
        CreateAccountResponseDto responseDto = accountsService.createAccount(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 계정 프로필(자기소개) 수정: PATCH /accounts/profile/{accountId}
    @PatchMapping("/profile/{accountId}")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable("accountId") Long accountId,
                                                            @RequestBody @Valid BioUpdateRequestDto requestDto) {
        AccountResponseDto responseDto = accountsService.updateAccount(accountId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 계정 논리적 삭제(탈퇴): PATCH /accounts/{accountId}
    @PatchMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountId") Long accountId) {
        accountsService.deleteAccount(accountId);  // 상태 변경만 수행
        return ResponseEntity.ok("message : 성공적으로 탈퇴되었습니다.");
    }

    // 계정 물리적 삭제: DELETE /accounts/{accountId}
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> physicalDeleteAccount(@PathVariable("accountId") Long accountId) {
        accountsService.physicalDeleteAccount(accountId);
        return ResponseEntity.ok("message : 성공적으로 탈퇴되었습니다.");
    }
}
