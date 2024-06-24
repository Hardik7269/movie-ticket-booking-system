package com.project.bms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1

@JsonSerialize
@JsonDeserialize
@Getter
@Setter

public class Weather {
	private Current current;
	private Location location;
	@Getter
	@Setter
	public class Current {
		@JsonProperty("last_updated")
		private String lastUpdated;
		@JsonProperty("temp_c")
		private double tempC;
		@JsonProperty("is_day")
		private int isDay;
		@JsonProperty("wind_kph")
		private double windKph;

		private int humidity;
		private int cloud;
		private double uv;
	}
	@Getter
	@Setter
	public class Location{
	    public String name;
	    public String region;
	    public String country;
	    public double lat;
	    public double lon;
	    public String localtime;
	}

}
