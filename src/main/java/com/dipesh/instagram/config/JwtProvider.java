package com.dipesh.instagram.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {

        String jwt  = Jwts.builder()
                .setIssuer("Dipesh Sinha")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return null;
    }

    public static String getEmailFromJwtToken(String jwt) {
        //Bearer token
        jwt = jwt.substring(7);

        Claims claims = Jwts.JwtParserBuilder
    }
}
