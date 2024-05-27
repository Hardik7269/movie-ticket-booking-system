package com.project.bms.dtos;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class ShowDto {
	private Time showTime;
	private Date showDate;
	private Integer theaterId;
	private Integer movieId;
}
