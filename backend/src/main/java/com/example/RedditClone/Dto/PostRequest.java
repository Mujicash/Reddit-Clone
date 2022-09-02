package com.example.RedditClone.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private Long id;
    private String postName;
    private String description;
    private String subredditName;
    private String url;

}
