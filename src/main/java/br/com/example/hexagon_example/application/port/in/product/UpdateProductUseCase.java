package br.com.example.hexagon_example.application.port.in.product;

import br.com.example.hexagon_example.application.command.UpdateProductCommand;
import br.com.example.hexagon_example.domain.model.Product;

public interface UpdateProductUseCase {

    Product update(Long id, UpdateProductCommand command);

}
