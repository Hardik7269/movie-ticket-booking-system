package com.project.bms.exceptations;

public class SeatNotAvailableExceptation extends RuntimeException{
	public SeatNotAvailableExceptation() {
		super("Seat Not Available !!");
	}
}
