package br.com.example.hexagon_example.application.command;

public record RegisterUserCommand(
        String login,
        String name,
        String password
) {
}
