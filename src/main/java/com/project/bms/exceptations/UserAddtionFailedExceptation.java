package com.project.bms.exceptations;

public class UserAddtionFailedExceptation extends RuntimeException{

    public UserAddtionFailedExceptation(){
        super("Something went wrong while adding New User !!");
    }
}
