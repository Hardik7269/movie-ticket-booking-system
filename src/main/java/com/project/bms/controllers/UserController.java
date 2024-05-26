package com.project.bms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bms.dtos.TicketDto;
import com.project.bms.dtos.UserDto;
import com.project.bms.service.UserService;

@RestController
public class UserController {

	public UserController(UserService userService) {
		this.userService = userService;
	}

	protected UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<String> registerUser(@RequestBody UserDto user) {

		String response = userService.addUser(user);

		return ResponseEntity.status(201).body(response);
	}

	@GetMapping("/showTickets/{userId}")
	public ResponseEntity<List<TicketDto>> getUsersTickets(@PathVariable long userId) {
		List<TicketDto> ticketList = userService.getAllTickets(userId);
		return ResponseEntity.ok(ticketList);
	}
}
