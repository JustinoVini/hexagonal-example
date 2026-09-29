package br.com.example.hexagon_example.application.service.auth;

import br.com.example.hexagon_example.application.command.AuthenticateCommand;
import br.com.example.hexagon_example.application.port.in.auth.AuthenticateUseCase;
import br.com.example.hexagon_example.domain.exception.InvalidCredentialsException;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.domain.port.out.PasswordEncoderPort;
import br.com.example.hexagon_example.domain.port.out.TokenProviderPort;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

import java.util.Locale;

/**
 * Service responsible for authenticating users.
 */
public class AuthenticateService implements AuthenticateUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    public AuthenticateService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort, TokenProviderPort tokenProviderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.tokenProviderPort = tokenProviderPort;
    }

    @Override
    public String authenticate(AuthenticateCommand command) {
        User user = findUserByLogin(command.login());
        validatePassword(command.password(), user.passwordHash());

        return tokenProviderPort.generate(user);
    }

    private User findUserByLogin(String login) {
        if (login == null || login.isBlank()) {
            throw invalidCredentials();
        }

        String normalizedLogin =
                login.trim().toLowerCase(Locale.ROOT);

        return userRepositoryPort.findByLogin(normalizedLogin)
                .orElseThrow(this::invalidCredentials);
    }

    private void validatePassword(String password, String passwordHash) {
        if (password == null || !passwordEncoderPort.matches(password, passwordHash)) {
            throw invalidCredentials();
        }
    }

    private InvalidCredentialsException invalidCredentials() {
        return new InvalidCredentialsException("Invalid login or password");
    }
}
