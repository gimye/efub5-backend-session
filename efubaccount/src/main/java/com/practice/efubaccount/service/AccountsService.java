package com.practice.efubaccount.service;

import com.practice.efubaccount.dto.AccountResponseDto;
import com.practice.efubaccount.dto.CreateAccountResponseDto;
import com.practice.efubaccount.dto.BioUpdateRequestDto;
import com.practice.efubaccount.dto.CreateAccountRequestDto;
import com.practice.efubaccount.entity.Account;
import com.practice.efubaccount.entity.AccountStatus;
import com.practice.efubaccount.repository.AccountsRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

//    private final AccountsRepository accountsRepository;
//
//    // 생성자 주입 (DI)
//    public AccountsService(AccountsRepository accountsRepository) {
//        this.accountsRepository = accountsRepository;
//    }
//
//    // 회원 단건 조회
//    public AccountResponseDto getAccount(Long accountId) {
//        Account account = accountsRepository.findByAccountId(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
//        return AccountResponseDto.from(account);
//    }
//
//    // 회원 생성
//    public CreateAccountResponseDto createAccount(CreateAccountRequestDto requestDto) {
//        // 이메일 중복 검사
//        if (accountsRepository.existsByEmail(requestDto.getEmail())) {
//            throw new IllegalArgumentException("이미 존재하는 email입니다. " + requestDto.getEmail());
//        }
//        Account account = requestDto.toEntity();
//        Account savedAccount = accountsRepository.save(account);
//        return CreateAccountResponseDto.from(savedAccount);
//    }
//
//    // 프로필(자기소개) 수정
//    public AccountResponseDto updateAccount(Long accountId, BioUpdateRequestDto requestDto) {
//        Account account = accountsRepository.findByAccountId(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
//        account.updateBio(requestDto.getBio());
//        Account updatedAccount = accountsRepository.save(account);
//        return AccountResponseDto.from(updatedAccount);
//    }
//
//    // 회원 논리적 삭제 (status 변경)
//    public void deleteAccount(Long accountId) {
//        Account account = accountsRepository.findByAccountId(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
//        account.changeStatus(AccountStatus.DEACTIVATED);
//        accountsRepository.save(account);
//    }
//
//    // 회원 물리적 삭제
//    public void physicalDeleteAccount(Long accountId) {
//        Account account = accountsRepository.findByAccountId(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
//        accountsRepository.delete(account);
//    }
}
