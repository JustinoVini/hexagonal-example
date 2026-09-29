package br.com.example.hexagon_example.application.service.product;

import br.com.example.hexagon_example.domain.exception.ProductNotFoundException;
import br.com.example.hexagon_example.domain.model.Product;
import br.com.example.hexagon_example.application.port.in.product.GetProductUseCase;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;

public class GetProductService implements GetProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product getById(Long id) {
        return productRepositoryPort.findById(id).orElseThrow(() -> new ProductNotFoundException(
                "Product not found with id: " + id
        ));
    }
}
