package com.project.bms.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bms.Repository.MovieRepository;
import com.project.bms.dtos.MovieDto;
import com.project.bms.entity.Movie;
import com.project.bms.entity.Show;
import com.project.bms.entity.ShowSeat;
import com.project.bms.exceptations.MovieNotFoundExceptation;
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
	
	public String movieRevenue(Integer id) throws MovieNotFoundExceptation {
		Optional<Movie> movieOpt = movieRepository.findById(id);
		if(movieOpt.isEmpty()) {
			throw new MovieNotFoundExceptation();
		}
		Movie movie = movieOpt.get();
		Integer revenue = 0;
		List<Show> showList = movie.getShows();
		for(Show show : showList) {
			Predicate<? super ShowSeat> predicate = s -> s.isAvailable()==false;
			List<Integer> collect = show.getShowSeats().stream().filter(predicate).map(seat -> seat.getPrice()).collect(Collectors.toList());
			for(Integer n : collect) {
				revenue += n;
			}
		}
		
		String movieName = movieRepository.findById(id).get().getMovieName();
		String response = "Total Collection of Movie "+ movieName + " is : " + revenue; 
		return response;
	}
}
