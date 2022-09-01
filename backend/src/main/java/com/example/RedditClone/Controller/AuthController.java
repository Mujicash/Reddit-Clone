package com.example.RedditClone.Controller;

import com.example.RedditClone.Dto.AuthenticationResponse;
import com.example.RedditClone.Dto.LoginRequest;
import com.example.RedditClone.Dto.RegisterRequest;
import com.example.RedditClone.Service.AuthService;
import com.example.RedditClone.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final LoginService loginService;

    public AuthController(AuthService authService, LoginService loginService) {
        this.authService = authService;
        this.loginService = loginService;
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
}