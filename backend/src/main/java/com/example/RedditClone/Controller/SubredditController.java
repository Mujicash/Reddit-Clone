package com.example.RedditClone.Controller;

import com.example.RedditClone.Dto.SubredditDto;
import com.example.RedditClone.Service.SubredditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService service;

    public SubredditController(SubredditService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        service.save(subredditDto);
        return new ResponseEntity<>(subredditDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        List<SubredditDto> subreddits = service.getAll();
        return new ResponseEntity<>(subreddits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> createSubreddit(@PathVariable Long id) {
        SubredditDto subreddit = service.getById(id);
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

}
