package com.epam.libraryservice.exception;

public class UserAlreadyHaveThreeBooks extends Throwable {
    public UserAlreadyHaveThreeBooks(String s) {
      super(s);
    }
}
