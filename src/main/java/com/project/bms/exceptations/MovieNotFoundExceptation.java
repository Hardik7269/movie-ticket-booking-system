package com.project.bms.exceptations;

public class MovieNotFoundExceptation extends RuntimeException{
	public MovieNotFoundExceptation() {
		super("Movie Not Found !!");
	}
}
