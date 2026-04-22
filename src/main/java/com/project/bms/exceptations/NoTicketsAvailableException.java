package com.project.bms.exceptations;

public class NoTicketsAvailableException extends RuntimeException{
	public NoTicketsAvailableException() {
		super("No tickets are booked by this user");
	}
}
