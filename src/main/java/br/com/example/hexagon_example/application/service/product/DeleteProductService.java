package br.com.example.hexagon_example.application.service.product;

import br.com.example.hexagon_example.application.port.in.product.DeleteProductUseCase;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;

public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void delete(Long id) {
        this.productRepositoryPort.deleteById(id);
    }
}
