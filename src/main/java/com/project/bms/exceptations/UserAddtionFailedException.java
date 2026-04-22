package com.project.bms.exceptations;

public class UserAddtionFailedException extends RuntimeException{

    public UserAddtionFailedException(){
        super("Something went wrong while adding New User !!");
    }
}
