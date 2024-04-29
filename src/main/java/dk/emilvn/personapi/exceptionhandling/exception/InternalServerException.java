package dk.emilvn.personapi.exceptionhandling.exception;

import dk.emilvn.personapi.exceptionhandling.exception.HttpException;

public class InternalServerException extends RuntimeException implements HttpException {
    public InternalServerException(String message) {
        super(message);
    }
}
