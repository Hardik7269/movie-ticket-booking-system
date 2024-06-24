package com.project.bms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.bms.entity.Weather;

@Service
public class WeatherService {
	private static final String API_KEY="ec7bbfb08a02438ba2c00516242406";
	
	private static final String URI = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";
	
	@Autowired
	private RestTemplate restTamplate;
	
	public Weather getWeather(String city) {
		String finalAPI = URI.replace("API_KEY", API_KEY).replace("CITY", city);
		
		//suing rest template we can call api
		//null is for header null
		//json to java object (Deserialization)
		try {
			ResponseEntity<Weather> response = restTamplate.exchange(finalAPI, HttpMethod.GET, null ,  Weather.class);
			Weather body = response.getBody();
			return body;
		} catch (Exception e) {
			 throw new RuntimeException("An error occurred while fetching weather data: " + e.getMessage(), e);
		}
		
	}
}
