package br.com.example.hexagon_example.domain.port.out;

import br.com.example.hexagon_example.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    boolean existsById(Long id);

    boolean existsByLogin(String login);

    void deleteById(Long id);

}
