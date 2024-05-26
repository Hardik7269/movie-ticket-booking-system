package com.project.bms.exceptations;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException() {
		super("User Not Found !!");
	}
}
