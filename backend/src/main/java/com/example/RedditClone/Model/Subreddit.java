package com.example.RedditClone.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "SUBREDDIT")
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  name;
    private String  description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subreddit")
    private List<Post> posts;
    private Instant created;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_subreddit_author"), nullable = false)
    private User    user;

    public Subreddit() {
    }

    public Subreddit(Long subredditId, String name, String description, Instant created, User user) {
        this.id          = subredditId;
        this.name        = name;
        this.description = description;
        this.created     = created;
        this.user        = user;
    }

    @Override
    public String toString() {
        return "id= " + id +
                "\nname= " + name +
                "\ndescription= " + description +
                "\ncreated=" + created +
                "\nuser=" + user;
    }
}
