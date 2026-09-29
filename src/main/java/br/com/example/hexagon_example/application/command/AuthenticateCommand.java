package br.com.example.hexagon_example.application.command;

public record AuthenticateCommand(
        String login,
        String password
) {
}
