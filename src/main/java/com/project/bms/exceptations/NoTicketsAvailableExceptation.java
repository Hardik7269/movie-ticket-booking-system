package com.project.bms.exceptations;

public class NoTicketsAvailableExceptation extends RuntimeException{
	public NoTicketsAvailableExceptation() {
		super("No tickets are booked by this user");
	}
}
