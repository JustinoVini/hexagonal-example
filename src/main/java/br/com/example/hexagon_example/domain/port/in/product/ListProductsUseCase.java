package br.com.example.hexagon_example.domain.port.in.product;

import br.com.example.hexagon_example.domain.model.Product;

import java.util.List;

public interface ListProductsUseCase {

    List<Product> listAll();

}
