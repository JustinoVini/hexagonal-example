package br.com.example.hexagon_example.infrastructure.config;

import br.com.example.hexagon_example.domain.model.Role;
import br.com.example.hexagon_example.domain.model.User;
import br.com.example.hexagon_example.domain.port.out.PasswordEncoderPort;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializerConfig {

    @Bean
    ApplicationRunner initializeAdmin(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoderPort,
            @Value("${app.admin.login:admin}") String login,
            @Value("${app.admin.name:Administrator}") String name,
            @Value("${app.admin.password}") String password
    ) {
        return args -> {
            if (userRepositoryPort.existsByLogin(login)) {
                return;
            }

            var admin = new User(
                    null,
                    login,
                    name,
                    passwordEncoderPort.encode(password),
                    Set.of(Role.USER, Role.ADMIN)
            );

            userRepositoryPort.save(admin);
        };
    }
}
