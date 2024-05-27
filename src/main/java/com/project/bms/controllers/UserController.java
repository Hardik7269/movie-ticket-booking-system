package com.project.bms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
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
		try {
			String response = userService.addUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/showTickets/{userId}")
	public ResponseEntity<List<TicketDto>> getUsersTickets(@PathVariable long userId) {
		try {
			List<TicketDto> ticketList = userService.getAllTickets(userId);
			return ResponseEntity.ok(ticketList);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
