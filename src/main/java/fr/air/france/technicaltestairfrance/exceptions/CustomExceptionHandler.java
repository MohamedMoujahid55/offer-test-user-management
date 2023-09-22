package fr.air.france.technicaltestairfrance.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a sample Spring class controller.
 *
 * <p>This class permit customize and control all application exceptions .
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> userNotFound(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false))
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false))
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request)
    {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach( error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
