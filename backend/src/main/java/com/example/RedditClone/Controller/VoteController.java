package com.example.RedditClone.Controller;

import com.example.RedditClone.Dto.VoteDto;
import com.example.RedditClone.Service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        service.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
