package com.neki.apiteste.domain.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.neki.apiteste.domain.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {

  @Value("${jwt_secret}")
  private String secret;

  public String generateToken(Authentication authentication){
    int expiryTime = 86400000;

    Date expiryDate = new Date(new Date().getTime() + expiryTime);

    Usuario usuario = (Usuario) authentication.getPrincipal();
    
    return Jwts.builder()
              .setSubject(usuario.getId().toString())
              .setIssuedAt(new Date())
              .setExpiration(expiryDate)
              .signWith(SignatureAlgorithm.HS512, secret)
              .compact();
  }

  public Optional<Long> getUsersId(String token){
    try{
      Claims claims = parse(token).getBody();

      return Optional.ofNullable(Long.parseLong(claims.getSubject()));
    }catch(Exception e){
      return Optional.empty();
    }
  }

  private Jws<Claims> parse(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
  }
}
