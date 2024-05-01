package ru.netology.server.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.server.entity.ErrorEntity;
import ru.netology.server.exception.InsufficientFunds;
import ru.netology.server.exception.InvalidCredentials;
import ru.netology.server.exception.NotFound;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ErrorEntity> handleInvalidCredentialsException(InvalidCredentials e) {
        return new ResponseEntity<>(new ErrorEntity(e.getMessage(), e.getId()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var error = e.getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ErrorEntity(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientFunds.class)
    public ResponseEntity<String> handleInsufficientFundsException(InsufficientFunds e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorEntity> handleNotFoundException(NotFound e) {
        return new ResponseEntity<>(new ErrorEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
