package br.com.example.hexagon_example.infrastructure.config;

import br.com.example.hexagon_example.adapter.out.persistence.ProductPersistenceAdapter;
import br.com.example.hexagon_example.adapter.out.persistence.UserPersistenceAdapter;
import br.com.example.hexagon_example.adapter.out.persistence.mapper.ProductPersistenceMapper;
import br.com.example.hexagon_example.adapter.out.persistence.mapper.UserPersistenceMapper;
import br.com.example.hexagon_example.adapter.out.persistence.repository.SpringDataProductRepository;
import br.com.example.hexagon_example.adapter.out.persistence.repository.SpringDataUserRepository;
import br.com.example.hexagon_example.adapter.out.security.JwtTokenProviderAdapter;
import br.com.example.hexagon_example.application.service.auth.AuthenticateService;
import br.com.example.hexagon_example.application.service.product.CreateProductService;
import br.com.example.hexagon_example.application.service.product.DeleteProductService;
import br.com.example.hexagon_example.application.service.product.GetProductService;
import br.com.example.hexagon_example.application.service.product.ListProductsService;
import br.com.example.hexagon_example.application.service.product.UpdateProductService;
import br.com.example.hexagon_example.application.service.user.DeleteUserService;
import br.com.example.hexagon_example.application.service.user.GetUserService;
import br.com.example.hexagon_example.application.service.user.ListUsersService;
import br.com.example.hexagon_example.application.service.user.RegisterUserService;
import br.com.example.hexagon_example.application.service.user.UpdateUserService;
import br.com.example.hexagon_example.domain.port.out.PasswordEncoderPort;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;
import br.com.example.hexagon_example.domain.port.out.TokenProviderPort;
import br.com.example.hexagon_example.domain.port.out.UserRepositoryPort;
import br.com.example.hexagon_example.infrastructure.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    ProductRepositoryPort productRepositoryPort(
            SpringDataProductRepository repository,
            ProductPersistenceMapper mapper
    ) {
        return new ProductPersistenceAdapter(repository, mapper);
    }

    @Bean
    UserRepositoryPort userRepositoryPort(
            SpringDataUserRepository repository,
            UserPersistenceMapper mapper
    ) {
        return new UserPersistenceAdapter(repository, mapper);
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtTokenProviderAdapter tokenProvider,
            UserRepositoryPort userRepositoryPort
    ) {
        return new JwtAuthenticationFilter(tokenProvider, userRepositoryPort);
    }

    @Bean
    AuthenticateService authenticateService(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoderPort,
            TokenProviderPort tokenProviderPort
    ) {
        return new AuthenticateService(userRepositoryPort, passwordEncoderPort, tokenProviderPort);
    }

    @Bean
    RegisterUserService registerUserService(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoderPort
    ) {
        return new RegisterUserService(userRepositoryPort, passwordEncoderPort);
    }

    @Bean
    GetUserService getUserService(UserRepositoryPort userRepositoryPort) {
        return new GetUserService(userRepositoryPort);
    }

    @Bean
    ListUsersService listUsersService(UserRepositoryPort userRepositoryPort) {
        return new ListUsersService(userRepositoryPort);
    }

    @Bean
    UpdateUserService updateUserService(UserRepositoryPort userRepositoryPort) {
        return new UpdateUserService(userRepositoryPort);
    }

    @Bean
    DeleteUserService deleteUserService(UserRepositoryPort userRepositoryPort) {
        return new DeleteUserService(userRepositoryPort);
    }

    @Bean
    CreateProductService createProductService(ProductRepositoryPort productRepositoryPort) {
        return new CreateProductService(productRepositoryPort);
    }

    @Bean
    GetProductService getProductService(ProductRepositoryPort productRepositoryPort) {
        return new GetProductService(productRepositoryPort);
    }

    @Bean
    ListProductsService listProductsService(ProductRepositoryPort productRepositoryPort) {
        return new ListProductsService(productRepositoryPort);
    }

    @Bean
    UpdateProductService updateProductService(ProductRepositoryPort productRepositoryPort) {
        return new UpdateProductService(productRepositoryPort);
    }

    @Bean
    DeleteProductService deleteProductService(ProductRepositoryPort productRepositoryPort) {
        return new DeleteProductService(productRepositoryPort);
    }
}
