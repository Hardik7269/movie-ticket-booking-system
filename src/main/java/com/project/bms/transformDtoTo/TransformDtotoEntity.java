package com.project.bms.transformDtoTo;

import org.springframework.stereotype.Component;

import com.project.bms.dtos.TicketDto;
import com.project.bms.dtos.UserDto;
import com.project.bms.entity.Ticket;
import com.project.bms.entity.User;

@Component
public class TransformDtotoEntity {

	public User userDtoToUser(UserDto udto) {

		return User.builder().uname(udto.getUserName()).age(udto.getAge()).email(udto.getEmail())
				.gender(udto.getGender()).build();
	}

	public TicketDto ticketToTicketDto(Ticket t) {
		return TicketDto.builder().username(t.getUser().getUname()).
				moviename(t.getShow().getMovie().getMovieName())
				.theatername(t.getShow().getTheater().getName())
				.purchaseTime(t.getPurchDateTime())
				.showTime(t.getShow().getTime())
				.showDate(t.getShow().getDate()).build();
	}
}
