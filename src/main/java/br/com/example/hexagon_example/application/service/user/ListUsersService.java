package br.com.example.hexagon_example.application.service.user;

import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.application.port.in.user.ListUsersUseCase;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

import java.util.List;

public class ListUsersService implements ListUsersUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public ListUsersService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<User> listAll() {
        return userRepositoryPort.findAll();
    }
}
