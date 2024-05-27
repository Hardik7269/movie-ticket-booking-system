package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.project.bms.entity.ShowSeat;

import lombok.Data;

@Data
public class TicketReciveDto {
	public Time showTime;
	public Date showDate;
	
	public String theaterName;
	public String movieName;
	public List<ShowSeat> selectSeats;
	
	public String username;
}
