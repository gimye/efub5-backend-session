package com.practice.blog.account.service;

import com.practice.blog.account.dto.AccountResponseDto;
import com.practice.blog.account.dto.CreateAccountResponseDto;
import com.practice.blog.account.dto.BioUpdateRequestDto;
import com.practice.blog.account.dto.CreateAccountRequestDto;
import com.practice.blog.account.entity.Account;
import com.practice.blog.account.entity.AccountStatus;
import com.practice.blog.account.repository.AccountsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;

    // 회원 단건 조회
    @Transactional
    public AccountResponseDto getAccount(Long accountId) {
        Account account = accountsRepository.findByAccountId(accountId).orElseThrow(()->new IllegalArgumentException("Account with id " + accountId + " does not exist"));
        return AccountResponseDto.from(account);
    }

    // 회원 생성
    @Transactional
    public CreateAccountResponseDto createAccount(CreateAccountRequestDto requestDto) {
        if(accountsRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Account account = requestDto.toEntity();
        Account savedAccount = accountsRepository.save(account);
        return CreateAccountResponseDto.from(savedAccount);
    }

    // 프로필(자기소개) 수정
    @Transactional
    public AccountResponseDto updateAccount(Long accountId, BioUpdateRequestDto requestDto) {
        Account account = accountsRepository.findByAccountId(accountId).orElseThrow(()->new IllegalArgumentException("Account with id " + accountId + " does not exist"));
        account.updateBio(requestDto.getBio());
        return AccountResponseDto.from(account);
    }

    // 회원 논리적 삭제 (status 변경)
    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = accountsRepository.findByAccountId(accountId).orElseThrow(()->new IllegalArgumentException("Account with id " + accountId + " does not exist"));
        account.changeStatus(AccountStatus.DEACTIVATED);
    }

    // 회원 물리적 삭제
    @Transactional
    public void physicalDeleteAccount(Long accountId) {
        Account account = accountsRepository.findByAccountId(accountId).orElseThrow(()-> new IllegalArgumentException("Account with id " + accountId + " does not exist"));
        accountsRepository.delete(account);
    }
}