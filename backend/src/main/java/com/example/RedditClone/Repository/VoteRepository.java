package com.example.RedditClone.Repository;

import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByPostAndUser(Post post, User user);

    List<Vote> findByPost(Post post);
}
