package com.project.bms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bms.Repository.MovieRepository;
import com.project.bms.Repository.ShowRepository;
import com.project.bms.Repository.TheaterRepository;
import com.project.bms.dtos.ShowDto;
import com.project.bms.entity.Movie;
import com.project.bms.entity.Show;
import com.project.bms.entity.Theater;
import com.project.bms.exceptations.MovieNotFoundExceptation;
import com.project.bms.exceptations.TheaterNotFoundExceptation;

import jakarta.transaction.Transactional;

@Service
public class ShowService {

	protected ShowRepository showRepository;
	protected TheaterRepository theaterRepository;
	protected MovieRepository movieRepository;
	
	public ShowService(ShowRepository showRepository, TheaterRepository theaterRepository,
			MovieRepository movieRepository) {
		super();
		this.showRepository = showRepository;
		this.theaterRepository = theaterRepository;
		this.movieRepository = movieRepository;
	}


	@Transactional
	public void addShow(ShowDto showDto) throws TheaterNotFoundExceptation,MovieNotFoundExceptation{
		Optional<Theater> theaterOp = theaterRepository.findById(showDto.getTheaterId());
		if(theaterOp.isEmpty()) {
			throw new TheaterNotFoundExceptation();
		}
		Optional<Movie> movieOp = movieRepository.findById(showDto.getMovieId());
		if(movieOp.isEmpty()) {
			throw new MovieNotFoundExceptation();
		}
		
		Movie movie = movieOp.get();
		Theater theater = theaterOp.get();
		
		Show newShow = Show.builder().date(showDto.getShowDate())
						.time(showDto.getShowTime())
						.movie(movie)
						.theater(theater)
						.build();
		movie.addMovieShows(newShow);
		theater.addTheaterShow(newShow);
		
		showRepository.save(newShow);
		movieRepository.save(movie);
		theaterRepository.save(theater);
	}
}
