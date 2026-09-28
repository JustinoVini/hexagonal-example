package br.com.example.hexagon_example.domain.exception;

public class UserAlreadyExistsException extends DomainException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
