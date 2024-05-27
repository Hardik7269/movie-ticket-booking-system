package com.project.bms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.bms.Repository.MovieRepository;
import com.project.bms.Repository.ShowRepository;
import com.project.bms.Repository.TheaterRepository;
import com.project.bms.dtos.ShowDto;
import com.project.bms.entity.Movie;
import com.project.bms.entity.Show;
import com.project.bms.entity.Theater;
import com.project.bms.exceptations.MovieNotFoundExceptation;
import com.project.bms.exceptations.ShowNotFoundExceptation;
import com.project.bms.exceptations.TheaterNotFoundExceptation;
import com.project.bms.transformDtoTo.TransformDto;

import jakarta.transaction.Transactional;

@Service
public class ShowService {

	protected ShowRepository showRepository;
	protected TheaterRepository theaterRepository;
	protected MovieRepository movieRepository;
	protected TransformDto transform;
	
	public ShowService(ShowRepository showRepository, TheaterRepository theaterRepository,
			MovieRepository movieRepository , TransformDto transform) {
		super();
		this.showRepository = showRepository;
		this.theaterRepository = theaterRepository;
		this.movieRepository = movieRepository;
		this.transform = transform;
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
		
		Show newShow = transform.showDtoToShow(showDto , theater , movie);
				
		movie.addMovieShows(newShow);
		theater.addTheaterShow(newShow);
		
		showRepository.save(newShow);
		movieRepository.save(movie);
		theaterRepository.save(theater);
	}

	public List<ShowDto> getTodayShow() throws ShowNotFoundExceptation {
		LocalDate today = LocalDate.now();
		Predicate<Show> predicate = show -> show.getDate().toLocalDate().equals(today);

		List<ShowDto> todayShows = showRepository.findAll().stream().filter(predicate)
				.map(show -> transform.showToShowDto(show)).collect(Collectors.toList());

		if (todayShows.isEmpty()) {
			throw new ShowNotFoundExceptation();
		}

		return todayShows;
	}
}
