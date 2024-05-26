package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDto {
	private String username;
	private String moviename;
	private String theatername;
	private LocalDateTime purchaseTime;
	private Time showTime;
	private Date showDate;
}
