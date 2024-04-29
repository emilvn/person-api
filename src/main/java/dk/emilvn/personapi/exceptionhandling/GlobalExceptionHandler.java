package dk.emilvn.personapi.exceptionhandling;

import dk.emilvn.personapi.exceptionhandling.exception.BadRequestException;
import dk.emilvn.personapi.exceptionhandling.exception.InternalServerException;
import dk.emilvn.personapi.exceptionhandling.exception.NotFoundException;
import dk.emilvn.personapi.exceptionhandling.exception.UnexpectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<?> handleInternalServerException(InternalServerException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<?> handleUnexpectedException(UnexpectedException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
