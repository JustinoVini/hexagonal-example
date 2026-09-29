package br.com.example.hexagon_example.application.service.product;

import br.com.example.hexagon_example.application.command.CreateProductCommand;
import br.com.example.hexagon_example.domain.model.Product;
import br.com.example.hexagon_example.application.port.in.product.CreateProductUseCase;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;

public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public CreateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product create(CreateProductCommand command) {
        var product = new Product(
                null,
                command.name(),
                command.quantity(),
                command.weight(),
                command.price()
        );

        return productRepositoryPort.save(product);
    }
}
