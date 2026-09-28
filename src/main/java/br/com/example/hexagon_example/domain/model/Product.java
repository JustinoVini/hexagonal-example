package br.com.example.hexagon_example.domain.model;

public record Product(
    Long id,
    String name,
    Integer quantity,
    double weight
) {
}
