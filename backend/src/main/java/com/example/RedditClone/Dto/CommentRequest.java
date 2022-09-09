package com.example.RedditClone.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private Long   postId;
    private String text;

    @Override
    public String toString() {
        return "CommentRequest{" + "postId=" + postId + ", text='" + text + '}';
    }
}
