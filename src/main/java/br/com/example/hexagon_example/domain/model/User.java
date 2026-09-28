package br.com.example.hexagon_example.domain.model;

import java.util.List;

public record User(
        Long id,
        String login,
        String name,
        String password,
        List<Role> roles
) {
}
