package br.com.example.hexagon_example.application.port.in.product;

import br.com.example.hexagon_example.application.command.CreateProductCommand;
import br.com.example.hexagon_example.domain.model.Product;

public interface CreateProductUseCase {

    Product create(CreateProductCommand command);

}
