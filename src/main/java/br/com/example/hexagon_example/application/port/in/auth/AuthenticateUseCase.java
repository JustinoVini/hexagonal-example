package br.com.example.hexagon_example.application.port.in.auth;

import br.com.example.hexagon_example.application.command.AuthenticateCommand;

public interface AuthenticateUseCase {

    String authenticate(AuthenticateCommand command);

}
