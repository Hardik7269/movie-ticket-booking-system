package com.project.bms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bms.dtos.TicketReceiveDto;
import com.project.bms.service.TicketService;

@RestController
public class TicketController {
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	protected TicketService ticketService;
	
	
	@PostMapping("/bookTicket")
	public ResponseEntity<String> bookTicket(@RequestBody TicketReceiveDto ticketInfo){
			ticketService.bookTicket(ticketInfo);
			return ResponseEntity.status(HttpStatus.CREATED).body("Ticket Booked !!");
	}
	
}
