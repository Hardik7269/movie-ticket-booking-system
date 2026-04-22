package com.project.bms.exceptations;

public class SeatNotAvailableException extends RuntimeException{
	public SeatNotAvailableException() {
		super("Seat Not Available !!");
	}
}
