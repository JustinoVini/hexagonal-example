package br.com.example.hexagon_example.domain.model;

import br.com.example.hexagon_example.domain.exception.DomainException;

import java.util.Locale;
import java.util.Set;

public record User(
        Long id,
        String login,
        String name,
        String passwordHash,
        Set<Role> roles
) {

    public User {
        if (login == null || login.isBlank()) {
            throw new DomainException("User login is required");
        }

        if (name == null || name.isBlank()) {
            throw new DomainException("User name is required");
        }

        if (passwordHash == null || passwordHash.isBlank()) {
            throw new DomainException("User password hash is required");
        }

        login = login.trim().toLowerCase(Locale.ROOT);
        name = name.trim();
        roles = roles == null || roles.isEmpty()
                ? Set.of(Role.USER)
                : Set.copyOf(roles);
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public User addRole(Role role) {
        if (role == null) {
            throw new DomainException("Role is required");
        }

        var updatedRoles = new java.util.HashSet<>(roles);
        updatedRoles.add(role);

        return new User(id, login, name, passwordHash, updatedRoles);
    }

    public User removeRole(Role role) {
        if (role == null) {
            throw new DomainException("Role is required");
        }

        if (role == Role.USER) {
            throw new DomainException("The default USER role cannot be removed");
        }

        var updatedRoles = new java.util.HashSet<>(roles);
        updatedRoles.remove(role);

        return new User(id, login, name, passwordHash, updatedRoles);
    }

}
