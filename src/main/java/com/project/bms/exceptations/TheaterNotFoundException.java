package com.project.bms.exceptations;

public class TheaterNotFoundException extends RuntimeException{
	public TheaterNotFoundException() {
		super("Theater Not Found Exceptation");
	}
}
