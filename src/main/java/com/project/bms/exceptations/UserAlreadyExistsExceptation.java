package com.project.bms.exceptations;

public class UserAlreadyExistsExceptation extends RuntimeException{
    public UserAlreadyExistsExceptation(){
        super("User Already exists");
    }
}
