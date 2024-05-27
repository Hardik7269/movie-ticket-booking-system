package com.project.bms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bms.dtos.MovieDto;
import com.project.bms.service.MovieService;

@RestController
public class MovieController {
	
	protected MovieService movieService;
	
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@PostMapping("/addMovie")
	public ResponseEntity<String> addMovie(@RequestBody MovieDto movieReqDto){
		try {
			String result = movieService.addMovie(movieReqDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
		}
	}
	
	@GetMapping("/box-office/{movieId}")
	public ResponseEntity<String> movieRevenue(@PathVariable Integer movieId){
		try {
			String result = movieService.movieRevenue(movieId);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
