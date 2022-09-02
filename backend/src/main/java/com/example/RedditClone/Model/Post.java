package com.example.RedditClone.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String postName;
    @Nullable
    private String    url;
    @Nullable
    @Lob
    private String    description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_post_author"))
    private User      user;
    private Instant   createdDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subreddit_id", foreignKey = @ForeignKey(name = "FK_post_subreddit"))
    private Subreddit subreddit;

    public Post() {
    }

    public Post(Long postId, String postName, @Nullable String url, @Nullable String description, User user,
                Instant createdDate, Subreddit subreddit) {
        this.id       = postId;
        this.postName = postName;
        this.url         = url;
        this.description = description;
        this.user        = user;
        this.createdDate = createdDate;
        this.subreddit   = subreddit;
    }

}
