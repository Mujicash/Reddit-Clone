package com.example.RedditClone.Service.User;

import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final UserRepository repository;

    public SessionService(UserRepository repository) {
        this.repository = repository;
    }

    public User getCurrentUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String      username  = principal.getUsername();

        return repository.findByUsername(username)
                         .orElseThrow(() -> new SpringRedditException("Username not found -> " + username));

    }
}
