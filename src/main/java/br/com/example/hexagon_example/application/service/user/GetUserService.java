package br.com.example.hexagon_example.application.service.user;

import br.com.example.hexagon_example.domain.exception.UserNotFoundException;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.application.port.in.user.GetUserUseCase;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

public class GetUserService implements GetUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User getById(Long id) {
        return userRepositoryPort.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("User not found with id: " + id);
        });
    }
}
