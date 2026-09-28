package br.com.example.hexagon_example.domain.port.in.auth;

public interface AuthenticateUseCase {

    String authenticate(String login, String password);

}
