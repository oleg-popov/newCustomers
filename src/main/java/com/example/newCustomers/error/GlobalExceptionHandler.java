package com.example.newCustomers.error;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            EmailIsAlreadyExistsException.class,
            PhoneIsAlreadyExistsException.class,
            NotFoundException.class,
            JwtAuthenticationException.class
    })
    public ResponseEntity<String>handlerExceptions(ResponseStatusException ex){
        String message = ex.getReason();
        return new ResponseEntity<>(message, ex.getStatus());
    }
    @Override
    @NonNull
    protected ResponseEntity<Object>handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                 @NonNull HttpHeaders headers,
                                                                 @NonNull HttpStatus status,
                                                                 @NonNull WebRequest request){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
