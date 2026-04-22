package com.project.bms.dtos;

import lombok.Builder;

@Builder
public record MovieDto(
		String movieName, 
		long duration, 
		String languate) {

}
