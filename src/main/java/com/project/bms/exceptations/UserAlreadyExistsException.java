package com.project.bms.exceptations;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("User Already exists");
    }
}
