package com.project.bms.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bms.Repository.MovieRepository;
import com.project.bms.Repository.ShowRepository;
import com.project.bms.Repository.TheaterRepository;
import com.project.bms.Repository.TicketRepository;
import com.project.bms.Repository.UserRepository;
import com.project.bms.dtos.TicketReciveDto;
import com.project.bms.entity.Movie;
import com.project.bms.entity.Show;
import com.project.bms.entity.ShowSeat;
import com.project.bms.entity.Theater;
import com.project.bms.entity.Ticket;
import com.project.bms.entity.User;
import com.project.bms.exceptations.MovieNotFoundExceptation;
import com.project.bms.exceptations.SeatNotAvailableExceptation;
import com.project.bms.exceptations.ShowNotFoundExceptation;
import com.project.bms.exceptations.TheaterNotFoundExceptation;
import com.project.bms.exceptations.UserNotFoundException;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    public TicketService(TicketRepository ticketRepository, ShowRepository showRepository,
                         UserRepository userRepository, TheaterRepository theaterRepository, 
                         MovieRepository movieRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.theaterRepository = theaterRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void bookTicket(TicketReciveDto ticketReciveDto) 
            throws TheaterNotFoundExceptation,MovieNotFoundExceptation, ShowNotFoundExceptation, ShowNotFoundExceptation {

        String username = ticketReciveDto.getUsername();

        User user = userRepository.findByUname(username)
                .orElseThrow(() -> new UserNotFoundException());

        Theater theater = theaterRepository.findByName(ticketReciveDto.getTheaterName())
                .orElseThrow(() -> new TheaterNotFoundExceptation());

        Movie movie = movieRepository.findByMovieName(ticketReciveDto.getMovieName())
                .orElseThrow(() -> new MovieNotFoundExceptation());

        Time showTime = ticketReciveDto.getShowTime();
        Date showDate = ticketReciveDto.getShowDate();
        Integer theaterId = theater.getId();
        Integer movieId = movie.getId();

        Show show = showRepository.findByTimeAndDateAndTheaterIdAndMovieId(showTime, showDate, theaterId, movieId)
                .orElseThrow(() -> new ShowNotFoundExceptation());

        List<ShowSeat> selectedSeats = ticketReciveDto.getSelectSeats();
        List<ShowSeat> showSeats = show.getShowSeats();

        for (ShowSeat seat : selectedSeats) {
        	
            Predicate<? super ShowSeat> predicate = s -> s.getSeatNo().equals(seat.getSeatNo()) && s.isAvailable();
			ShowSeat availableSeat = showSeats.stream()
                    .filter(predicate)
                    .findFirst()
                    .orElseThrow(() -> new SeatNotAvailableExceptation());

            availableSeat.setAvailable(false);
        }

        showRepository.save(show);

        Ticket bookedTicket = Ticket.builder()
                .purchDateTime(LocalDateTime.now())
                .show(show)
                .user(user)
                .build();

        ticketRepository.save(bookedTicket);
    }
}
