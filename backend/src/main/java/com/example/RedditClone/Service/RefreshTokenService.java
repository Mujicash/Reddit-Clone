package com.example.RedditClone.Service;

import com.example.RedditClone.Dto.AuthenticationResponse;
import com.example.RedditClone.Dto.RefreshTokenRequest;
import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Model.RefreshToken;
import com.example.RedditClone.Repository.RefreshTokenRepository;
import com.example.RedditClone.Security.JwtProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repository;
    private final JwtProvider            jwtProvider;

    public RefreshTokenService(RefreshTokenRepository repository, JwtProvider jwtProvider) {
        this.repository  = repository;
        this.jwtProvider = jwtProvider;
    }

    public RefreshToken generateToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());

        return repository.save(refreshToken);
    }

    @Transactional

    public AuthenticationResponse refreshToken(@NotNull RefreshTokenRequest request) {
        validateRefreshToken(request.getRefreshToken());
        String token = jwtProvider.generateTokenWithUsername(request.getUsername());

        return AuthenticationResponse.builder().authenticationToken(token).refreshToken(request.getRefreshToken())
                                     .expireAt(Instant.now().plusMillis(jwtProvider.getExpiration()))
                                     .username(request.getUsername()).build();
    }

    private void validateRefreshToken(String token) {
        repository.findByToken(token).orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
    }

    @Transactional
    public void deleteRefreshToken(String token) {
        repository.deleteByToken(token);
    }

}
