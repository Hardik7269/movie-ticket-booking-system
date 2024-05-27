package com.project.bms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bms.dtos.MovieDto;
import com.project.bms.service.MovieService;

@RestController
public class MovieController {
	
	protected MovieService movieService;
	
	@PostMapping("/addMovie")
	public ResponseEntity<String> addMovie(@RequestBody MovieDto movieReqDto){
		try {
			String result = movieService.addMovie(movieReqDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
		}
	}
}
