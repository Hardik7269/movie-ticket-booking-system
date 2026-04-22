package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
public record TicketDto (
	 String username,
	 String moviename,
	 String theatername,
	 List<String> seatNo,
	 LocalDateTime purchaseTime,
	 Time showTime,
	 Date showDate) {
}
