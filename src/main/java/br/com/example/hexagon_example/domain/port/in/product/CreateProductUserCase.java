package br.com.example.hexagon_example.domain.port.in.product;

import br.com.example.hexagon_example.domain.model.Product;

public interface CreateProductUserCase {

    Product create(Product product);

}
