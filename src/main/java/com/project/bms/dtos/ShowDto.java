package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.Builder;


@Builder
public record ShowDto (
	 Time showTime,
	 Date showDate,
	 Integer theaterId,
	 Integer movieId) {
}
