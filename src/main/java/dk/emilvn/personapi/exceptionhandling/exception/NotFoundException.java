package dk.emilvn.personapi.exceptionhandling.exception;

import dk.emilvn.personapi.exceptionhandling.exception.HttpException;

public class NotFoundException extends RuntimeException implements HttpException {
    public NotFoundException(String message) {
        super(message);
    }
}
