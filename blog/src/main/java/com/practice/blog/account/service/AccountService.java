package com.practice.blog.account.service;

import com.practice.blog.account.dto.response.AccountResponseDto;
//import com.practice.blog.account.dto.response.CreateAccountResponseDto;
//import com.practice.blog.account.dto.request.BioUpdateRequestDto;
//import com.practice.blog.account.dto.request.CreateAccountRequestDto;
import com.practice.blog.account.entity.Account;
//import com.practice.blog.account.entity.AccountDocument;
import com.practice.blog.account.entity.AccountStatus;
//import com.practice.blog.account.repository.AccountDocumentRepository;
import com.practice.blog.account.repository.AccountsRepository;

import com.practice.blog.global.exception.BlogException;
import com.practice.blog.global.exception.ExceptionCode;
//import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.Map;
//import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor

public class AccountService {

    private final AccountsRepository accountsRepository;

//    private final RedisTemplate<String , Object> redisTemplate; // Redis와 통신
//    private HashOperations<String , String , Object> hashOperations; // Redis에 저장할 Hash 객체 선언
//    private static final String ACCOUNT_CACHE_KEY = "Account:";  //key의 접두사
//
//    // Mongo DB용 repository
//    private final AccountDocumentRepository accountDocumentRepository;
//
//    // 초기화
//
//
//    // 회원 생성
//    @Transactional
//    public CreateAccountResponseDto createAccount(CreateAccountRequestDto requestDto) {
//        if(accountsRepository.existsByEmail(requestDto.getEmail())) {
//            throw new IllegalArgumentException("Email already exists");
//        }
//        Account account = requestDto.toEntity();
//        Account savedAccount = accountsRepository.save(account);
//
//        // Redis 해시에 이메일과 닉네임 저장
//
//
//        // 만료 시간 설정 (30분)
//
//
//        // MongoDB에 저장
//
//        return CreateAccountResponseDto.from(savedAccount);
//    }
//
//
//
//    // 회원 수정 (bio, nickname)
//    @Transactional
//    public AccountResponseDto updateAccount(Long accountId, BioUpdateRequestDto requestDto) {
//        Account account = findByAccountId(accountId);
//        account.updateBio(requestDto.getBio());
//        account.updateNickname(requestDto.getNickname());
//
//        // Redis에서 닉네임 업데이트
//
//
//        // MongoDB에서 닉네임 업데이트
//
//        return AccountResponseDto.from(account);
//    }
//
//
//    // 회원 물리적 삭제
//    @Transactional
//    public void physicalDeleteAccount(Long accountId) {
//        Account account = findByAccountId(accountId);
//
//        // Redis에서 삭제
//
//
//        // MySQL에서 삭제
//        accountsRepository.delete(account);
//
//        // MongoDB에서 삭제
//
//    }
//
//    // Redis에서 ID로 이메일 조회
//    @Transactional(readOnly = true)
//    public String findEmailByIdFromRedis(Long id) {
//
//
//        // Redis 해시에서 값 조회
//
//            // DB에서 조회
//
//
//            // DB에서 조회한 정보를 Redis에 저장
//
//        }
//
//    }
//
//    // MongoDB에서 ID로 닉네임 조회
//    @Transactional(readOnly = true)
//    public String findNicknameByIdFromMongo(Long id) {
//
//    }


    // 회원 논리적 삭제 (status 변경)
    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = findByAccountId(accountId);
        account.changeStatus(AccountStatus.DEACTIVATED);
    }

    // 회원 단건 조회
    @Transactional(readOnly=true)
    public AccountResponseDto getAccount(Long accountId) {
        Account account = findByAccountId(accountId);
        return AccountResponseDto.from(account);
    }

    @Transactional(readOnly=true)
    public Account findByAccountId(Long accountId) {
        return accountsRepository.findByAccountId(accountId)
                .orElseThrow(()-> new BlogException(ExceptionCode.ACCOUNT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Account findByEmail(String email){
        return accountsRepository.findByEmail(email)
                .orElseThrow(()-> new BlogException(ExceptionCode.ACCOUNT_NOT_FOUND));
    }

    // 이메일 중복 체크
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return accountsRepository.existsByEmail(email);
    }
}