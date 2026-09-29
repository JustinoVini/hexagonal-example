package br.com.example.hexagon_example.application.service.user;

import br.com.example.hexagon_example.application.command.RegisterUserCommand;
import br.com.example.hexagon_example.application.port.in.user.RegisterUserUseCase;
import br.com.example.hexagon_example.domain.exception.UserAlreadyExistsException;
import br.com.example.hexagon_example.domain.model.Role;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.domain.port.out.PasswordEncoderPort;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

import java.util.Set;

public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public RegisterUserService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public User register(RegisterUserCommand command) {
        validateLoginAvailability(command.login());

        String passwordHash = passwordEncoderPort.encode(command.password());

        var user = new User(
                null,
                command.login(),
                command.name(),
                passwordHash,
                Set.of(Role.USER)
        );

        return userRepositoryPort.save(user);
    }

    private void validateLoginAvailability(String login) {
        if (userRepositoryPort.existsByLogin(login)) {
            throw new UserAlreadyExistsException(
                    "User already exists with login: " + login
            );
        }
    }
}
