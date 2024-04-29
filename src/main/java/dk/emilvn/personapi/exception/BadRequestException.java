package dk.emilvn.personapi.exception;

public class BadRequestException extends RuntimeException implements HttpException{
    public BadRequestException(String message) {
        super(message);
    }
}
