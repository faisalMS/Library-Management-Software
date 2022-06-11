package com.example.librarymanagementsoftware.Exception;

public class UsersNotFoundException extends RuntimeException{

    public UsersNotFoundException(String message){
        super(message);
    }
}