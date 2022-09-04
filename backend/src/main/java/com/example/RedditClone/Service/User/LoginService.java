package com.example.RedditClone.Service.User;

import com.example.RedditClone.Dto.AuthenticationResponse;
import com.example.RedditClone.Dto.LoginRequest;
import com.example.RedditClone.Security.JwtProvider;
import com.example.RedditClone.Service.RefreshTokenService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@Transactional
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider           jwtProvider;
    private final RefreshTokenService   refreshTokenService;

    public LoginService(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                        RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider           = jwtProvider;
        this.refreshTokenService   = refreshTokenService;
    }


    public AuthenticationResponse login(@NotNull LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return AuthenticationResponse.builder().authenticationToken(token)
                                     .refreshToken(refreshTokenService.generateToken().getToken())
                                     .expireAt(Instant.now().plusMillis(jwtProvider.getExpiration()))
                                     .username(loginRequest.getUsername()).build();
    }

}
