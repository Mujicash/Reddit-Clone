package com.example.RedditClone.Security;

import com.example.RedditClone.Service.User.UserDetailsServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest req, @NotNull HttpServletResponse res,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);

            if(jwtProvider.validateToken(token)) {
                String      username = jwtProvider.getUsernameFromToken(token);
                System.out.println(username + "-1");
                UserDetails details  = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(details, null,
                                                                                                   details.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        filterChain.doFilter(req, res);
    }

    private String getToken(HttpServletRequest request) throws Exception {
        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer")) {
            throw new Exception("No authorization header");
        }

        return header.replace("Bearer ", "");
    }
}
