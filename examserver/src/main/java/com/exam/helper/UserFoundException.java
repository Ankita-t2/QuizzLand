package com.exam.helper;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("user with this username is aleready in db !! try with another");
    }

    public UserFoundException(String msg) {
        super((msg));
    }
}
