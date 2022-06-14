package edu.miu.blog.exception.handler;

import edu.miu.blog.exception.util.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> postNotFoundExc(MethodArgumentNotValidException ex) {
        ApiError error = ApiError
                .builder()
                .description(ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> {
                            return fieldError.getField()+" -> "+fieldError.getDefaultMessage();
                        }).collect(Collectors.joining(",")))
//                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
