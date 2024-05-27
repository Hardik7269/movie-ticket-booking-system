package com.project.bms.transformDtoTo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.bms.dtos.TicketDto;
import com.project.bms.dtos.UserDto;
import com.project.bms.entity.ShowSeat;
import com.project.bms.entity.Ticket;
import com.project.bms.entity.User;

@Component
public class TransformDtotoEntity {

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
}
