package com.example.RedditClone.Repository;

import com.example.RedditClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
}
