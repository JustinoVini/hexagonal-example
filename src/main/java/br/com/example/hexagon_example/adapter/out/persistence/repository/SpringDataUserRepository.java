package br.com.example.hexagon_example.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.example.hexagon_example.adapter.out.persistence.entity.UserEntity;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    boolean existsByLogin(String login);

}
