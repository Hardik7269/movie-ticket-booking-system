package com.project.bms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bms.dtos.ShowDto;
import com.project.bms.entity.Show;
import com.project.bms.service.ShowService;

@RestController
public class ShowConntroller {
	
	protected ShowService showService;
	public ShowConntroller(ShowService showService) {
		super();
		this.showService = showService;
	}

	@PostMapping("/addShow")
	public ResponseEntity<String> addShow(@RequestBody ShowDto showReqDto){
		try {
			showService.addShow(showReqDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Show Added Successfully !");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
		}
	}
	
	@GetMapping("/showToday")
	public ResponseEntity<List<Show>> showTodayShows(){
		try {
			List<Show> shows = showService.getTodayShow();
			return ResponseEntity.ok(shows);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
