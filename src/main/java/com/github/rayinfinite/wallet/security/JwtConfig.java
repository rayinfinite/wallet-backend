package com.github.rayinfinite.wallet.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Getter
public class JwtConfig {
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    public String createToken(Long userId) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withClaim("userId", userId)
                .withExpiresAt(Instant.now().plus(Duration.ofDays(30))) //30天过期
                .sign(algorithm);
    }

    public Long validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        return decodedJWT.getClaim("userId").asLong();
    }
}
