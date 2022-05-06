package com.epam.libraryservice.exception;

public class UserAlreadyHaveThisBook extends Exception {
    public UserAlreadyHaveThisBook(String s) {
        super(s);
    }
}
