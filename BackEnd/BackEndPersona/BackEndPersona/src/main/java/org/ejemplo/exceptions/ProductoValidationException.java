package org.ejemplo.exceptions;

public class ProductoValidationException extends RuntimeException {

    public ProductoValidationException(String message) {
        super(message);
    }
}
