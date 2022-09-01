package com.example.RedditClone.Model;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "SUBREDDIT")
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;
    private String     description;
    /*@OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;*/
    private Instant    created;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_subreddit_author"),nullable = false)
    private User       user;

    public Subreddit() {
    }

    public Subreddit(Long subredditId, String name, String description, Instant created, User user) {
        this.id   = subredditId;
        this.name = name;
        this.description = description;
        this.created     = created;
        this.user        = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
