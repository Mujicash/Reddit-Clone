package com.example.RedditClone.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String username;
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    private String  email;
    private Instant created;
    private boolean enabled;

    public User() {
    }

    public User(Long userId, String userName, String password, String email, Instant created, boolean enabled) {
        this.id       = userId;
        this.username = userName;
        this.password = password;
        this.email    = email;
        this.created  = created;
        this.enabled  = enabled;
    }

    @Override
    public String toString() {
        return "id=" + id +
                "\nusername= " + username +
                "\npassword= " + password +
                "\nemail= " + email +
                "\ncreated= " + created +
                "\nenabled= " + enabled;
    }
}