package com.example.RedditClone.Repository;

import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.Subreddit;
import com.example.RedditClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);

    Optional<Post> findByPostName(String name);
}
