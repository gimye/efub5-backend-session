package com.practice.blog.post.domain;

import com.practice.blog.account.entity.Account;
import com.practice.blog.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account writer;

    private Long viewCount;

    @Builder
    public Post(String title, String content, Account writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = 0L;
    }

    public void increaseViewCount() {
        viewCount++;
    }

    public void changeContent(Post post) {
        this.title = post.title;
        this.content = post.content;
    }
}
