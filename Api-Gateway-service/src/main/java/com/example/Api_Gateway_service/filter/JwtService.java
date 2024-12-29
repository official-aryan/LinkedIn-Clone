package com.example.Api_Gateway_service.filter;


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



    public String getUserIdFromToken(String token)
    {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(getsecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return body.getSubject();
    }

}
