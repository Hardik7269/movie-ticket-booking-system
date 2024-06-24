package com.project.bms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.bms.Repository.UserRepository;
import com.project.bms.dtos.TicketDto;
import com.project.bms.dtos.TicketReciveDto;
import com.project.bms.dtos.TicketResponseDTO;
import com.project.bms.dtos.UserDto;
import com.project.bms.entity.User;
import com.project.bms.entity.Weather;
import com.project.bms.exceptations.NoTicketsAvailableExceptation;
import com.project.bms.exceptations.UserAddtionFailedExceptation;
import com.project.bms.exceptations.UserAlreadyExistsExceptation;
import com.project.bms.exceptations.UserNotFoundException;
import com.project.bms.transformDtoTo.TransformDto;

@Service
public class UserService {

    public UserService(UserRepository userRepository, TransformDto transform , WeatherService weatherService) {
		this.userRepository = userRepository;
		this.transform = transform;
		this.weatherService = weatherService;
	}
    private UserRepository userRepository;
    private TransformDto transform;
    private WeatherService weatherService;
    


	public String addUser(UserDto userdto) throws UserAlreadyExistsExceptation, UserAddtionFailedExceptation{
        try {
            User newUser = transform.userDtoToUser(userdto);
            if(userRepository.findByEmail(newUser.getEmail())!= null){
                throw new UserAlreadyExistsExceptation();
            }
            userRepository.save(newUser);
            return "User Added Sucessfully";
        } catch (Exception e) {
            throw new UserAddtionFailedExceptation();
        }
    }
	
	public TicketResponseDTO getAllTickets(long uId) throws UserNotFoundException , NoTicketsAvailableExceptation{
		User user = userRepository.findById(uId).orElseThrow( () -> new UserNotFoundException());
		if(user.getTikcets().isEmpty()) {
			throw new NoTicketsAvailableExceptation();
		}
		
		 List<TicketDto> collect = user.getTikcets().stream().map(transform::ticketToTicketDto).collect(Collectors.toList());
		 Weather weather =  weatherService.getWeather("Valsad"); //do not have city in the ticket entity : issue* Fix this
		 
		 if(weather == null) {
			 System.err.println("Weather is not getting");
		 }
		 
		 return TicketResponseDTO.builder()
				 	.ticketList(collect)
				 	.weather(weather)
				 	.build();
//		return user.getTikcets().stream().map(t -> transform.ticketToTicketDto(t)).collect(Collectors.toList());
	}
}
