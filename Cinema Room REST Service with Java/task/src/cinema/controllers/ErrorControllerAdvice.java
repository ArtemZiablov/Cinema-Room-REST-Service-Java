package cinema.controllers;

import cinema.exceptions.BusinessException;
import cinema.exceptions.WrongPasswordException;
import cinema.models.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ErrorControllerAdvice {
    
    @ExceptionHandler
    ResponseEntity<ErrorDTO> exceptionHandler(BusinessException ex){
        if(ex instanceof WrongPasswordException){
            return ResponseEntity.status(UNAUTHORIZED).body(new ErrorDTO(ex.getMessage())); // так
        }
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), BAD_REQUEST); // або так
    }
}
