package com.example.SistemaReservaAutomotiva.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class JwtService {

    private final String secretKey = "Chave muito secreta, confia em mim, eu não mentiria :) ";
    private final Integer expirationDays = 30;
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    private final String issuer = "Sistema de reserva automotiva - by Henrique Silveira Soares";


    public String generateToken(String subject){
        return JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(expirationTime())
                .withSubject(subject)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getTokenSubject(String token){
        if(token == null) {
            throw new NoSuchElementException("Invalid token");
        }

        if(!token.startsWith("Bearer ")) {
            throw new NoSuchElementException("Invalid token");
        }

        token = token.substring(7);

        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    public Instant expirationTime(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(expirationDays).toInstant();
    }

}
