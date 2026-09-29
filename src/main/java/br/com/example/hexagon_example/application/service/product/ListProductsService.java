package br.com.example.hexagon_example.application.service.product;

import br.com.example.hexagon_example.domain.model.Product;
import br.com.example.hexagon_example.application.port.in.product.ListProductsUseCase;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;

import java.util.List;

public class ListProductsService implements ListProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ListProductsService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<Product> listAll() {
        return productRepositoryPort.findAll();
    }
}
