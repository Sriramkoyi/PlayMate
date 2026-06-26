package com.example.sport.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.secret}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private long jwtexpiration;
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	public String generateToken(String email) {
		return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+jwtexpiration)).signWith(getSigningKey()).compact();
	}
	public String extractUsername(String token) {
		Claims claims=Jwts.parser().verifyWith((javax.crypto.SecretKey)getSigningKey()).build().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}
	public boolean isTokenValid(String token,String email) {
		String username=extractUsername(token);
		return username.equals(email);
	}
	public boolean isTokenvalid(String token,UserDetails userDetails) {
		String username=extractUsername(token);
		return username.equals(userDetails.getUsername());
	}
}
