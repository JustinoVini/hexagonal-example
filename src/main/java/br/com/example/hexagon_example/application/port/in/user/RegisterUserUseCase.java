package br.com.example.hexagon_example.application.port.in.user;

import br.com.example.hexagon_example.application.command.RegisterUserCommand;
import br.com.example.hexagon_example.domain.model.User;

public interface RegisterUserUseCase {

    User register(RegisterUserCommand command);

}
