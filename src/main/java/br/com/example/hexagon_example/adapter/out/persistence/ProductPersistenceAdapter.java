package br.com.example.hexagon_example.adapter.out.persistence;

import br.com.example.hexagon_example.adapter.out.persistence.mapper.ProductPersistenceMapper;
import br.com.example.hexagon_example.adapter.out.persistence.repository.SpringDataProductRepository;
import br.com.example.hexagon_example.domain.exception.ProductNotFoundException;
import br.com.example.hexagon_example.domain.model.Product;
import br.com.example.hexagon_example.domain.port.out.ProductRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository repository;
    private final ProductPersistenceMapper mapper;

    public ProductPersistenceAdapter(SpringDataProductRepository repository, ProductPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Product update(Long id, Product product) {
        validateInformedId(id);

        var productToUpdate = new Product(
                id,
                product.name(),
                product.quantity(),
                product.weight(),
                product.price()
        );

        return mapper.toDomain(repository.save(mapper.toEntity(productToUpdate)));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void validateInformedId(Long id) {
        if (id == null || !repository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }
}
