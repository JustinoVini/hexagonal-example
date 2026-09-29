package br.com.example.hexagon_example.adapter.out.persistence.mapper;

import br.com.example.hexagon_example.adapter.out.persistence.entity.ProductEntity;
import br.com.example.hexagon_example.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity product);

}
