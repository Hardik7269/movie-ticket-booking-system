package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import com.project.bms.entity.Weather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDto {
	private String username;
	private String moviename;
	private String theatername;
	private List<String> seatNo;
	private LocalDateTime purchaseTime;
	private Time showTime;
	private Date showDate;
}
