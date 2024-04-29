package dk.emilvn.personapi.exception;

public class InternalServerException extends RuntimeException implements HttpException{
    public InternalServerException(String message) {
        super(message);
    }
}
