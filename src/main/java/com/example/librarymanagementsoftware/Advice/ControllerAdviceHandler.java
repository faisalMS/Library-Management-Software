package com.example.librarymanagementsoftware.Advice;

import com.example.librarymanagementsoftware.Exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviceHandler {
    Logger logger = LoggerFactory.getLogger(ControllerAdviceHandler.class);
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgument(MethodArgumentNotValidException methodArgumentNVE){
        logger.warn("MethodArgumentNotValidException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodArgumentNVE.getFieldError().getDefaultMessage());
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrity(DataIntegrityViolationException dataIntegrityVE){
        logger.warn("DataIntegrityViolationException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((dataIntegrityVE.getCause().getMessage()));
    }
    @ExceptionHandler(value = InvalidIDException.class)
    public ResponseEntity handleDataIntegrity(InvalidIDException invalidIDE){
        logger.warn("InvalidIDException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidIDE.getCause().getMessage());
    }
    @ExceptionHandler(value = BooksNotFoundException.class)
    public ResponseEntity  handleDataIntegrity(BooksNotFoundException booksNotFoundException){
        logger.warn("BooksNotFoundException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(booksNotFoundException.getCause().getMessage());
    }
    @ExceptionHandler(value = CartNotFoundException.class)
    public ResponseEntity  handleDataIntegrity(CartNotFoundException cartNotFoundException){
        logger.warn("CartNotFoundException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cartNotFoundException.getCause().getMessage());
    }
    @ExceptionHandler(value = LoanNotFoundException.class)
    public ResponseEntity  handleDataIntegrity(LoanNotFoundException loanNotFoundException){
        logger.warn("LoanNotFoundException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loanNotFoundException.getCause().getMessage());
    }
    @ExceptionHandler(value = UsersNotFoundException.class)
    public ResponseEntity  handleDataIntegrity(UsersNotFoundException usersNotFoundException){
        logger.warn("UsersNotFoundException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usersNotFoundException.getCause().getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception exception) {
        logger.error("Exception");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getCause().getMessage());
    }
}