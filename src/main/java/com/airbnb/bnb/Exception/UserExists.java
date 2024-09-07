package com.airbnb.bnb.Exception;

public class UserExists extends RuntimeException{
    public UserExists(String message) {
        super(message);
    }
}
