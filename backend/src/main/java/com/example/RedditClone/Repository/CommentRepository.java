package com.example.RedditClone.Repository;

import com.example.RedditClone.Dto.CommentResponse;
import com.example.RedditClone.Model.Comment;
import com.example.RedditClone.Model.Post;
import com.example.RedditClone.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
