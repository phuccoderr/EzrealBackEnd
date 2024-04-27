package com.ezreal.service;

import com.ezreal.model.response.AccountInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET = "!@#$SAMKASMDBNBHasjhdbASKJDBAKWJHDJKWAqweqweqwaxzdz";
    // GENERATE TOKEN
    public String generateToken(AccountInfo info) {
        Map<String, String> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(info.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) //5phut
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // DECODE TOKEN
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check Name User
    public String extractUserName(String token) {
        return extractClaim(token, Claims -> Claims.getSubject());
    }

    // Check Expiration Token
    public Boolean validateToken(String token) {
        return !isTokenExpirated(token);
    }

    private boolean isTokenExpirated(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims -> Claims.getExpiration());
    }

    public boolean isValidJWT(String token) {
        int numberOfPeriods = 0;
        for (char c : token.toCharArray()) {
            if (c == '.') {
                numberOfPeriods++;
            }
        }
        return numberOfPeriods == 2;
    }
    // Access Token
    public boolean checkToken(String token) {
        String userName;
        if(token != null && isValidJWT(token)) {
            token = token.substring(7);
            userName = extractUserName(token);
        } else {
            return false;
        }
        if (userName != null) {
            if (!validateToken(token)) {
                return false;
            };
        }
        return true;
    }
}
