package br.com.example.hexagon_example.domain.exception;

public class UserNotFoundException extends DomainException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
