package br.com.example.hexagon_example.domain.port.in.user;

import br.com.example.hexagon_example.domain.model.User;

public interface RegisterUserUseCase {

    User register(User user);

}
