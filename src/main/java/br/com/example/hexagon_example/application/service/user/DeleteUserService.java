package br.com.example.hexagon_example.application.service.user;

import br.com.example.hexagon_example.application.port.in.user.DeleteUserUseCase;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void delete(Long id) {
        userRepositoryPort.deleteById(id);
    }
}
