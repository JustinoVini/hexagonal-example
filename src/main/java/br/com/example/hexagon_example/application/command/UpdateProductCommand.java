package br.com.example.hexagon_example.application.command;

import java.math.BigDecimal;

public record UpdateProductCommand(
        String name,
        int quantity,
        BigDecimal weight,
        BigDecimal price
) {
}
