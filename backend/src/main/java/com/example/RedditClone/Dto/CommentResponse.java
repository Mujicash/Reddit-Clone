package com.example.RedditClone.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long    id;
    private Long    postId;
    private String  text;
    private String  username;
    private String  duration;
}
