package com.project.bms.dtos;

import java.util.List;

import com.project.bms.entity.Weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
	private List<TicketDto> ticketList;
	private Weather weather;
}
