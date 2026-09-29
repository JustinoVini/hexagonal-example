package br.com.example.hexagon_example.adapter.out.persistence.repository;

import br.com.example.hexagon_example.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {
}
