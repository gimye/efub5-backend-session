package com.practice.blog.follow.service;

import com.practice.blog.account.entity.Account;
import com.practice.blog.account.service.AccountService;
import com.practice.blog.follow.domain.Follow;
import com.practice.blog.follow.dto.request.FollowRequestDto;
import com.practice.blog.follow.dto.response.FollowListResponseDto;
import com.practice.blog.follow.dto.response.FollowStatusResponseDto;
import com.practice.blog.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final AccountService accountService;
    private final FollowRepository followRepository;

    // 팔로우 추가
    public FollowStatusResponseDto addFollow(Long accountId, FollowRequestDto followRequestDto){
        Account follower = accountService.findByAccountId(accountId);
        Account following = accountService.findByAccountId(followRequestDto.getFollowingId());
        followRepository.save(followRequestDto.toEntity(follower, following));
        boolean isFollowed = followRepository.existsByFollowerAndFollowing(follower, following);

        return FollowStatusResponseDto.of(following, isFollowed);
    }

    // 팔로우 여부 확인
    @Transactional(readOnly = true)
    public FollowStatusResponseDto isFollowing(Long followerId, Long followingId){
        Account follower = accountService.findByAccountId(followerId);
        Account following = accountService.findByAccountId(followingId);
        boolean isFollowed = followRepository.existsByFollowerAndFollowing(follower, following);

        return FollowStatusResponseDto.of(following, isFollowed);
    }

    // 팔로우 & 팔로잉 리스트 전체 조회
    @Transactional(readOnly = true)
    public FollowListResponseDto getFollowList(Long accountId) {
        Account account = accountService.findByAccountId(accountId);
        List<Follow> followers = followRepository.findAllByFollowing(account);
        List<Follow> followings = followRepository.findAllByFollower(account);

        return FollowListResponseDto.of(followers, followings);
    }

    // 팔로우 삭제
    public FollowStatusResponseDto deleteFollow(Long accountId, Long followingId){
        Account follower = accountService.findByAccountId(accountId);
        Account following = accountService.findByAccountId(followingId);
        Follow findFollow = followRepository.findByFollowerAndFollowing(follower, following);
        followRepository.delete(findFollow);

        boolean isFollowed = followRepository.existsByFollowerAndFollowing(follower, following);

        return FollowStatusResponseDto.of(following, isFollowed);
    }
}
