package com.example.RedditClone.Service.User;

import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username)
                         .orElseThrow(() -> new SpringRedditException("Username not found -> " + username));
    }
}
