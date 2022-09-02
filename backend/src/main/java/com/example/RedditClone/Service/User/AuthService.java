package com.example.RedditClone.Service.User;

import com.example.RedditClone.Dto.RegisterRequest;
import com.example.RedditClone.Exception.SpringRedditException;
import com.example.RedditClone.Model.NotificationEmail;
import com.example.RedditClone.Model.User;
import com.example.RedditClone.Model.VerificationToken;
import com.example.RedditClone.Repository.UserRepository;
import com.example.RedditClone.Repository.VerificationTokenRepository;
import com.example.RedditClone.Service.MailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final PasswordEncoder             passwordEncoder;
    private final UserRepository              userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService                 mailService;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository repository,
                       VerificationTokenRepository verificationTokenRepository, MailService mailService) {
        this.passwordEncoder             = passwordEncoder;
        this.userRepository              = repository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService                 = mailService;
    }

    public void RegisterUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your Account", user.getEmail(),
                                                   "Thanks you for signing up to Reddit Clone, " + "please click on the below url to activate your account: " + "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);

        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        System.out.println(user);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
