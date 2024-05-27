package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowDto {
	private Time showTime;
	private Date showDate;
	private Integer theaterId;
	private Integer movieId;
}
