package com.example.RedditClone.Model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;

@Entity
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
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Long getId() {
        return id;
    }

    public void setId(Long postId) {
        this.id = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }
}
