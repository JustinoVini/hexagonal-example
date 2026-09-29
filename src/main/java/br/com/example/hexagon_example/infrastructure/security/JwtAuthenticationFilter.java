package br.com.example.hexagon_example.infrastructure.security;

import br.com.example.hexagon_example.adapter.out.security.JwtTokenProviderAdapter;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProviderAdapter tokenProvider;
    private final UserRepositoryPort userRepositoryPort;

    public JwtAuthenticationFilter(JwtTokenProviderAdapter tokenProvider, UserRepositoryPort userRepositoryPort) {
        this.tokenProvider = tokenProvider;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        extractBearerToken(request)
                .flatMap(tokenProvider::validateAndGetSubject)
                .flatMap(userRepositoryPort::findByLogin)
                .ifPresent(this::authenticate);

        filterChain.doFilter(request, response);
    }

    private java.util.Optional<String> extractBearerToken(
            HttpServletRequest request
    ) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return java.util.Optional.empty();
        }

        String token = authorization.substring(7).trim();

        return token.isEmpty()
                ? java.util.Optional.empty()
                : java.util.Optional.of(token);
    }

    private void authenticate(User user) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        var authorities = user.roles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(
                        "ROLE_" + role.name()
                ))
                .toList();

        var authentication = new UsernamePasswordAuthenticationToken(
                user.login(),
                null,
                authorities
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }
}
