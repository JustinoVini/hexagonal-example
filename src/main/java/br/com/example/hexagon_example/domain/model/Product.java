package br.com.example.hexagon_example.domain.model;

import br.com.example.hexagon_example.domain.exception.DomainException;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        int quantity,
        BigDecimal weight,
        BigDecimal price
) {

    public Product {
        if (name == null || name.isBlank()) {
            throw new DomainException("Product name is required");
        }

        if (quantity < 0) {
            throw new DomainException("Product quantity cannot be negative");
        }

        if (weight == null || weight.signum() <= 0) {
            throw new DomainException("Product weight must be positive");
        }

        if (price == null || price.signum() < 0) {
            throw new DomainException("Product price cannot be negative");
        }

        name = name.trim();
    }

    public Product addStock(int amount) {
        if (amount <= 0) {
            throw new DomainException("Amount must be positive");
        }

        return new Product(id, name, quantity, weight, price);
    }

    public Product removeStock(int amount) {
        if (amount <= 0) {
            throw new DomainException("Amount must be positive");
        }

        if (amount > quantity) {
            throw new DomainException("Insufficient product stock");
        }

        return new Product(id, name, quantity - amount, weight, price);
    }

    public Product changePrice(BigDecimal newPrice) {
        return new Product(id, name, quantity, weight, newPrice);
    }

    public BigDecimal inventoryValue() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}
