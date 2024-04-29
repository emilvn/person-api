package dk.emilvn.personapi.exception;

public class UnexpectedException extends RuntimeException implements HttpException{
    public UnexpectedException(String message) {
        super("An unknown error occured: " + message);
    }
}
