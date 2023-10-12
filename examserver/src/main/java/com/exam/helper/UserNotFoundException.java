package com.exam.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("user with this username not found in dabase");
    }
    public  UserNotFoundException(String msg){
        super((msg));
    }

}
