package br.com.example.hexagon_example.application.service.product;

import br.com.example.hexagon_example.application.command.UpdateProductCommand;
import br.com.example.hexagon_example.domain.exception.ProductNotFoundException;
import br.com.example.hexagon_example.domain.model.Product;
import br.com.example.hexagon_example.application.port.in.product.UpdateProductUseCase;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;

import static org.springframework.util.ObjectUtils.isEmpty;

public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public UpdateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product update(Long id, UpdateProductCommand command) {
        validateIfIdExists(id);
        var updateProduct = new Product(
                id,
                command.name(),
                command.quantity(),
                command.weight(),
                command.price()
        );
        return productRepositoryPort.save(updateProduct);
    }

    private void validateIfIdExists(Long id) {
        if (isEmpty(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

}
