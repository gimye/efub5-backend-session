package com.practice.efubaccount.service;

import com.practice.efubaccount.dto.AccountResponseDto;
import com.practice.efubaccount.dto.CreateAccountResponseDto;
import com.practice.efubaccount.dto.BioUpdateRequestDto;
import com.practice.efubaccount.dto.CreateAccountRequestDto;
import com.practice.efubaccount.entity.Account;
import com.practice.efubaccount.entity.AccountStatus;
import com.practice.efubaccount.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsService {

//    private final AccountsRepository accountsRepository;
//
//    // 회원 단건 조회
//    public AccountResponseDto getAccount(Long accountId) {
//    }
//
//    // 회원 생성
//    public CreateAccountResponseDto createAccount(CreateAccountRequestDto requestDto) {
//    }
//
//    // 프로필(자기소개) 수정
//    public AccountResponseDto updateAccount(Long accountId, BioUpdateRequestDto requestDto) {
//    }
//
//    // 회원 논리적 삭제 (status 변경)
//    public void deleteAccount(Long accountId) {
//    }
//
//    // 회원 물리적 삭제
//    public void physicalDeleteAccount(Long accountId) {
//    }
}