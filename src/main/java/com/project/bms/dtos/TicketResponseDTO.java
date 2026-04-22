package com.project.bms.dtos;

import java.util.List;

import com.project.bms.entity.Weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record TicketResponseDTO (
	 List<TicketDto> ticketList,
	 Weather weather) {
}
