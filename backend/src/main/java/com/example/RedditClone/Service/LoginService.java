package com.example.RedditClone.Service;

import com.example.RedditClone.Dto.AuthenticationResponse;
import com.example.RedditClone.Dto.LoginRequest;
import com.example.RedditClone.Security.JwtProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider           jwtProvider;

    public LoginService(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider           = jwtProvider;
    }


    public AuthenticationResponse login(@NotNull LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return AuthenticationResponse.builder().authenticationToken(token).username(loginRequest.getUsername()).build();
    }

}
