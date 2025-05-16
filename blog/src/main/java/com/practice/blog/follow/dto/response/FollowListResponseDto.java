package com.practice.blog.follow.dto.response;

import com.practice.blog.follow.domain.Follow;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder @Getter
public class FollowListResponseDto {

    private List<SingleFollower> followerList;
    private List<SingleFollowing> followingList;
    private int followerCount;
    private int followingCount;

    @Getter
    @Builder
    public static class SingleFollower{
        private Long followerId;
        private String followerNickname;
        private String email;

        public static SingleFollower from(Follow follow) {
            return SingleFollower.builder()
                    .followerId(follow.getFollowId())
                    .followerNickname(follow.getFollower().getNickname())
                    .email(follow.getFollower().getEmail())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class SingleFollowing {
        private Long followingId;
        private String followingNickname;
        private String email;

        public static SingleFollowing from(Follow follow) {
            return SingleFollowing.builder()
                    .followingId(follow.getFollowId())
                    .followingNickname(follow.getFollowing().getNickname())
                    .email(follow.getFollowing().getEmail())
                    .build();
        }
    }

    public static FollowListResponseDto of(List<Follow> followers ,List<Follow> followings) {
        return FollowListResponseDto.builder()
                .followerList(followers.stream().map(SingleFollower::from).collect(Collectors.toList()))
                .followingList(followings.stream().map(SingleFollowing::from).collect(Collectors.toList()))
                .followerCount(followers.size())
                .followingCount(followings.size())
                .build();
    }
}
