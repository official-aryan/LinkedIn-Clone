package com.example.user_service.service;

import com.example.user_service.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getsecretKey()
    {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user)
    {
         return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*10))
                .signWith(getsecretKey())
                .compact();

    }

    public Long getUserIdFromToken(String token)
    {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(getsecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.valueOf(body.getSubject());
    }

}
