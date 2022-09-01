package com.example.RedditClone.Repository;

import com.example.RedditClone.Model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Subreddit findByName(String name);
}
