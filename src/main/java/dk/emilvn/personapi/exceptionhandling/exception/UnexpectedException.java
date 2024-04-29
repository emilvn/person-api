package dk.emilvn.personapi.exceptionhandling.exception;

import dk.emilvn.personapi.exceptionhandling.exception.HttpException;

public class UnexpectedException extends RuntimeException implements HttpException {
    public UnexpectedException(String message) {
        super("An unknown error occured: " + message);
    }
}
