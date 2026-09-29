package br.com.example.hexagon_example.application.service.user;

import br.com.example.hexagon_example.domain.exception.UserNotFoundException;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.application.port.in.user.UpdateUserUseCase;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User update(Long id, User user) {
        var existingUser = findExistingUser(id);
        var updateUser = new User(
                existingUser.id(),
                user.login(),
                user.name(),
                existingUser.passwordHash(),
                existingUser.roles()
        );
        return userRepositoryPort.save(updateUser);
    }

    private User findExistingUser(Long id) {
        if (id == null) {
            throw new UserNotFoundException("User id is required");
        }

        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with id: " + id
                ));
    }

}
