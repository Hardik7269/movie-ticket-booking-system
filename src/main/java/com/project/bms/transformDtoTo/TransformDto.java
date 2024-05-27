package com.project.bms.transformDtoTo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.bms.dtos.MovieDto;
import com.project.bms.dtos.ShowDto;
import com.project.bms.dtos.TicketDto;
import com.project.bms.dtos.UserDto;
import com.project.bms.entity.Movie;
import com.project.bms.entity.Show;
import com.project.bms.entity.ShowSeat;
import com.project.bms.entity.Theater;
import com.project.bms.entity.Ticket;
import com.project.bms.entity.User;

@Component
public class TransformDto {

	public User userDtoToUser(UserDto udto) {

		return User.builder().uname(udto.getUserName()).age(udto.getAge()).email(udto.getEmail())
				.gender(udto.getGender()).build();
	}

	public TicketDto ticketToTicketDto(Ticket t) {
//		List<String> seats = t.getShow().getShowSeats().stream().map(s -> s.getSeatNo()).collect(Collectors.toList());
//		List<String> seats = t.getShow().getShowSeats().stream().map(ShowSeat::getSeatNo).collect(Collectors.toList());
		Predicate<? super ShowSeat> predicate = s -> s.isAvailable()==false;
		List<String> seats = t.getShow().getShowSeats().stream().filter(predicate).map(ShowSeat :: getSeatNo).collect(Collectors.toList());
		return TicketDto.builder().username(t.getUser().getUname()).
				moviename(t.getShow().getMovie().getMovieName())
				.theatername(t.getShow().getTheater().getName())
				.seatNo(seats)
				.purchaseTime(t.getPurchDateTime())
				.showTime(t.getShow().getTime())
				.showDate(t.getShow().getDate()).build();
	}
	
	public Show showDtoToShow(ShowDto showDto , Theater theater , Movie movie) {
		return Show.builder().date(showDto.getShowDate())
		.time(showDto.getShowTime())
		.movie(movie)
		.theater(theater)
		.build();
	}
	
	public ShowDto showToShowDto(Show show) {
		return ShowDto.builder().showTime(show.getTime())
				.showDate(show.getDate())
				.theaterId(show.getTheater().getId())
				.movieId(show.getMovie().getId())
				.build();
	}
	
	public Movie movieDtoToMovie(MovieDto m) {
		return Movie.builder().movieName(m.getMovieName())
			.moviereleaseDate(LocalDate.now())
			.duration(m.getDuration())
			.language(m.getLanguate())
			.build();
	}
}
