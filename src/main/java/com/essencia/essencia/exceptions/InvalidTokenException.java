package com.essencia.essencia.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Token inválido ou expirado.");
    }
    public InvalidTokenException(String message) {
        super(message);
    }
}
