package dk.emilvn.personapi.exceptionhandling.exception;

public interface HttpException{
    static RuntimeException of(int statusCode, String message) {
        return switch (statusCode) {
            case 400 -> new BadRequestException(message);
            case 404 -> new NotFoundException(message);
            case 500 -> new InternalServerException(message);
            default -> new UnexpectedException(message);
        };
    }
}
