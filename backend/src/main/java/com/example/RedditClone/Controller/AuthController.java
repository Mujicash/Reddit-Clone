package com.example.RedditClone.Controller;

import com.example.RedditClone.Dto.AuthenticationResponse;
import com.example.RedditClone.Dto.LoginRequest;
import com.example.RedditClone.Dto.RefreshTokenRequest;
import com.example.RedditClone.Dto.RegisterRequest;
import com.example.RedditClone.Service.RefreshTokenService;
import com.example.RedditClone.Service.User.AuthService;
import com.example.RedditClone.Service.User.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
    @RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, LoginService loginService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.loginService = loginService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest) {
        authService.RegisterUser(registerRequest);
        return new ResponseEntity<>("User registration successfully", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationRes = loginService.login(loginRequest);
        return new ResponseEntity<>(authenticationRes, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthenticationResponse authenticationRes = refreshTokenService.refreshToken(request);
        return new ResponseEntity<>(authenticationRes, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest request) {
        refreshTokenService.deleteRefreshToken(request.getRefreshToken());
        return new ResponseEntity<>("Refresh Token deleted successfully!!!", HttpStatus.OK);
    }
}
