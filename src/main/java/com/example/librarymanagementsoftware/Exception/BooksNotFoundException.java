package com.example.librarymanagementsoftware.Exception;

public class BooksNotFoundException extends RuntimeException{

    public BooksNotFoundException(String message){
        super(message);
    }
}