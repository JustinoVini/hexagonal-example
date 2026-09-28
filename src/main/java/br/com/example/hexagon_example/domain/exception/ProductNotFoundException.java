package br.com.example.hexagon_example.domain.exception;

public class ProductNotFoundException extends DomainException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
