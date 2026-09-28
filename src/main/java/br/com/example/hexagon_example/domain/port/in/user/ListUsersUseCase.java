package br.com.example.hexagon_example.domain.port.in.user;

import br.com.example.hexagon_example.domain.model.User;

import java.util.List;

public interface ListUsersUseCase {

    List<User> listAll();

}
