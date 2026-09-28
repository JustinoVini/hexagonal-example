package br.com.example.hexagon_example.domain.port.in.user;

import br.com.example.hexagon_example.domain.model.User;

public interface GetUserUseCase {

    User getById(Long id);

}
