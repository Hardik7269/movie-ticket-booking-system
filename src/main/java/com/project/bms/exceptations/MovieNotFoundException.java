package com.project.bms.exceptations;

public class MovieNotFoundException extends RuntimeException{
	public MovieNotFoundException() {
		super("Movie Not Found !!");
	}
}
