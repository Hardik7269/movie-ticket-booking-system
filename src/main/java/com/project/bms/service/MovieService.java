package com.project.bms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bms.Repository.MovieRepository;
import com.project.bms.dtos.MovieDto;
import com.project.bms.entity.Movie;
import com.project.bms.transformDtoTo.TransformDto;

@Service
public class MovieService {
	
	protected MovieRepository movieRepository;
	protected TransformDto transform;
	
	public MovieService(MovieRepository movieRepository, TransformDto transform) {
		super();
		this.movieRepository = movieRepository;
		this.transform = transform;
	}
	@Transactional
	public String addMovie(MovieDto movieDto) {
		Movie m = transform.movieDtoToMovie(movieDto);
		movieRepository.save(m);
		return "Movie Successfully Added !!";
	}
}
