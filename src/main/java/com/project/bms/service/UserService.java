package com.project.bms.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.bms.Repository.UserRepository;
import com.project.bms.dtos.TicketDto;
import com.project.bms.dtos.UserDto;
import com.project.bms.entity.Ticket;
import com.project.bms.entity.User;
import com.project.bms.exceptations.NoTicketsAvailableExceptation;
import com.project.bms.exceptations.UserAddtionFailedExceptation;
import com.project.bms.exceptations.UserAlreadyExistsExceptation;
import com.project.bms.exceptations.UserNotFoundException;
import com.project.bms.transformDtoTo.TransformDtotoEntity;

@Service
public class UserService {

    public UserService(UserRepository userRepository, TransformDtotoEntity transformDtoToEntity) {
		this.userRepository = userRepository;
		this.transformDtoToEntity = transformDtoToEntity;
	}
    protected UserRepository userRepository;
    protected TransformDtotoEntity transformDtoToEntity;


	public String addUser(UserDto userdto) throws UserAlreadyExistsExceptation, UserAddtionFailedExceptation{
        try {
            User newUser = transformDtoToEntity.userDtoToUser(userdto);
            if(userRepository.findByEmail(newUser.getEmail())!= null){
                throw new UserAlreadyExistsExceptation();
            }
            userRepository.save(newUser);
            return "User Added Sucessfully";
        } catch (Exception e) {
            throw new UserAddtionFailedExceptation();
        }
    }
	
	public List<TicketDto> getAllTickets(long uId) throws UserNotFoundException , NoTicketsAvailableExceptation{
		User user = userRepository.findById(uId).orElseThrow( () -> new UserNotFoundException());
		if(user.getTikcets().isEmpty()) {
			throw new NoTicketsAvailableExceptation();
		}
		
		return user.getTikcets().stream().map(transformDtoToEntity::ticketToTicketDto).collect(Collectors.toList());
//		return user.getTikcets().stream().map(t -> transformDtoToEntity.ticketToTicketDto(t)).collect(Collectors.toList());
	}
}
