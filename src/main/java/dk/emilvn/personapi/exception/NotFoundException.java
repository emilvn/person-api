package dk.emilvn.personapi.exception;

public class NotFoundException extends RuntimeException implements HttpException{
    public NotFoundException(String message) {
        super(message);
    }
}
