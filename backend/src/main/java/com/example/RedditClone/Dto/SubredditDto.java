package com.example.RedditClone.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {

    private Long    id;
    private String  name;
    private String  description;
    private Integer numberOfPosts;
}
