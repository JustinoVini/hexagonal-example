package br.com.example.hexagon_example.adapter.out.persistence;

import br.com.example.hexagon_example.adapter.out.persistence.mapper.UserPersistenceMapper;
import br.com.example.hexagon_example.adapter.out.persistence.repository.SpringDataUserRepository;
import br.com.example.hexagon_example.domain.exception.UserNotFoundException;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

public class UserPersistenceAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;
    private final UserPersistenceMapper mapper;

    public UserPersistenceAdapter(SpringDataUserRepository repository, UserPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        var entity = mapper.toEntity(user);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public User update(Long id, User user) {
        validateInformedId(id);

        var userToUpdate = new User(
                id,
                user.login(),
                user.name(),
                user.passwordHash(),
                user.roles()
        );

        return mapper.toDomain(repository.save(mapper.toEntity(userToUpdate)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void validateInformedId(Long id) {
        if (id == null || !repository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

}
