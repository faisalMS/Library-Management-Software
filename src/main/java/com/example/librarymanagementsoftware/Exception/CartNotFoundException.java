package com.example.librarymanagementsoftware.Exception;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(String message){
        super(message);
    }
}