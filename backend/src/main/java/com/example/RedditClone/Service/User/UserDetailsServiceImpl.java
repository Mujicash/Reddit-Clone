package com.example.RedditClone.Service.User;

import com.example.RedditClone.Model.User;
import com.example.RedditClone.Repository.UserRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        System.out.println(username + "-2");
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username " + username));
        System.out.println(username + "-3");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                                                      user.isEnabled(), true, true, true,
                                                                      getAuthorities());
    }

    private @NotNull @Unmodifiable Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }
}
