package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.project.bms.entity.ShowSeat;

import lombok.Builder;

@Builder
 public record TicketReceiveDto (
	 Time showTime,
	 Date showDate,
	
	 String theaterName,
	 String movieName,
	 List<ShowSeat> selectSeats,
	
	 String username) {}
