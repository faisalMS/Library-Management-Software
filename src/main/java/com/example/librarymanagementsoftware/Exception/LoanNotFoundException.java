package com.example.librarymanagementsoftware.Exception;

public class LoanNotFoundException extends RuntimeException{

    public LoanNotFoundException(String message){
        super(message);
    }
}