package com.example.RedditClone.Service.Post;

import com.example.RedditClone.Dto.PostResponse;
import com.example.RedditClone.Mapper.PostMapper;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.PostRepository;
import com.example.RedditClone.Service.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostUserService {

    private final PostRepository repository;
    private final PostMapper  mapper;
    private final UserService userService;

    public PostUserService(PostRepository repository, PostMapper mapper, UserService userService) {
        this.repository  = repository;
        this.mapper      = mapper;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts(String username) {
        User user = userService.getUserByUsername(username);

        return repository.findByUser(user).stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

}
