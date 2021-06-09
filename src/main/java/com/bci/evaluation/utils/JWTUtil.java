package com.bci.evaluation.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

    private static final String SECRET_KEY="evaluacion-user-be-api";
    
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName=extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    
    private static Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
    
    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        
        final Claims claims=extratAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extratAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private static String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
