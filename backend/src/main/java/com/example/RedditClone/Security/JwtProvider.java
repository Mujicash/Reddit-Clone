package com.example.RedditClone.Security;

import com.example.RedditClone.Exception.SpringRedditException;
import io.jsonwebtoken.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int    expiration;

    public String generateToken(@NotNull Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();

        return generateTokenWithUsername(principal.getUsername());
    }

    public String generateTokenWithUsername(String username) {
        return Jwts.builder().setIssuer("self").setIssuedAt(new Date())
                   .setExpiration(new Date(new Date().getTime() + expiration)).setSubject(username)
                   .claim("scope", "ROLE_USER").signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch(MalformedJwtException e) {
            logger.error("token mal formado");
        } catch(UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch(ExpiredJwtException e) {
            logger.error("token expirado");
        } catch(IllegalArgumentException e) {
            logger.error("token vacío");
        }
        return false;
    }

    public int getExpiration() {
        return expiration;
    }
}
