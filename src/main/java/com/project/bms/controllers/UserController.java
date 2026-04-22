package com.project.bms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bms.dtos.TicketResponseDTO;
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
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/showTickets/{userId}")
	public ResponseEntity<TicketResponseDTO> getUsersTickets(@PathVariable long userId) {
			TicketResponseDTO ticketList = userService.getAllTickets(userId);
			return ResponseEntity.ok(ticketList);
	}
}
