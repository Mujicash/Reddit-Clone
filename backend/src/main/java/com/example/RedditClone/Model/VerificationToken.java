package com.example.RedditClone.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Setter
@Getter
@Table(name = "token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Instant expiryDate;

    public VerificationToken() {
    }

    public VerificationToken(Long id, String token, User user, Instant expiryDate) {
        this.id         = id;
        this.token      = token;
        this.user       = user;
        this.expiryDate = expiryDate;
    }
}
