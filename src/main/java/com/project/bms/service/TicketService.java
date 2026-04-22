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
import com.project.bms.dtos.TicketReceiveDto;
import com.project.bms.entity.Movie;
import com.project.bms.entity.Show;
import com.project.bms.entity.ShowSeat;
import com.project.bms.entity.Theater;
import com.project.bms.entity.Ticket;
import com.project.bms.entity.User;
import com.project.bms.exceptations.MovieNotFoundException;
import com.project.bms.exceptations.SeatNotAvailableException;
import com.project.bms.exceptations.ShowNotFoundException;
import com.project.bms.exceptations.TheaterNotFoundException;
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
    public void bookTicket(TicketReceiveDto ticketReciveDto) 
            throws TheaterNotFoundException,MovieNotFoundException, ShowNotFoundException, ShowNotFoundException {

        String username = ticketReciveDto.username();

        User user = userRepository.findByUname(username)
                .orElseThrow(() -> new UserNotFoundException());

        Theater theater = theaterRepository.findByName(ticketReciveDto.theaterName())
                .orElseThrow(() -> new TheaterNotFoundException());

        Movie movie = movieRepository.findByMovieName(ticketReciveDto.movieName())
                .orElseThrow(() -> new MovieNotFoundException());

        Time showTime = ticketReciveDto.showTime();
        Date showDate = ticketReciveDto.showDate();
        Integer theaterId = theater.getId();
        Integer movieId = movie.getId();

        Show show = showRepository.findByTimeAndDateAndTheaterIdAndMovieId(showTime, showDate, theaterId, movieId)
                .orElseThrow(() -> new ShowNotFoundException());

        List<ShowSeat> selectedSeats = ticketReciveDto.selectSeats();
        List<ShowSeat> showSeats = show.getShowSeats();

        for (ShowSeat seat : selectedSeats) {
            Predicate<? super ShowSeat> predicate = s -> s.getSeatNo().equals(seat.getSeatNo()) && s.isAvailable();
			ShowSeat availableSeat = showSeats.stream()
                    .filter(predicate)
                    .findFirst()
                    .orElseThrow(() -> new SeatNotAvailableException());

            availableSeat.setAvailable(false);
        }
        
        theater.addTheaterShow(show);
        movie.addMovieShows(show);

        Ticket bookedTicket = Ticket.builder()
                .purchDateTime(LocalDateTime.now())
                .show(show)
                .user(user)
                .build();
        
        show.addShowTicket(bookedTicket);
        user.addUserTicket(bookedTicket);
        
        showRepository.save(show);
    }
}
