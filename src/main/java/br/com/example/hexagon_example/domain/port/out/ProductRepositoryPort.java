package br.com.example.hexagon_example.domain.port.out;

import br.com.example.hexagon_example.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Product update(Long id, Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);

}
