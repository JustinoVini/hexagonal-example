package br.com.example.hexagon_example.adapter.out.security;

import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.domain.port.out.TokenProviderPort;
import br.com.example.hexagon_example.infrastructure.security.JwtProperties;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProviderAdapter implements TokenProviderPort {

    private final SecretKey key;
    private final Duration expiration;

    public JwtTokenProviderAdapter(JwtProperties properties) {
        if (properties.secret() == null || properties.secret().isBlank()) {
            throw new IllegalArgumentException("JWT secret is required");
        }
        if (properties.expiration() == null || properties.expiration().isZero()
                || properties.expiration().isNegative()) {
            throw new IllegalArgumentException("JWT expiration must be positive");
        }

        this.key = Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8));
        this.expiration = properties.expiration();
    }

    @Override
    public String generate(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.login())
                .claim("roles", user.roles().stream().map(Enum::name).toList())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(key)
                .compact();
    }

    // usado pelo JwtAuthenticationFilter, não faz parte da port
    public Optional<String> validateAndGetSubject(String token) {
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        try {
            String subject = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            return Optional.ofNullable(subject);
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
