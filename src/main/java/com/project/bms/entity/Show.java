package com.project.bms.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    private Time time;
    private Date date;
    
    @ManyToOne
    @JsonBackReference("movie-shows")
    @JoinColumn
    private Movie movie;
    
    @ManyToOne
    @JsonBackReference("theater-shows")
    @JoinColumn
    private Theater theater;
    
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonManagedReference("show-seats")
    private List<ShowSeat> showSeats = new ArrayList<>();
    
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonManagedReference("show-tickets")
    private List<Ticket> ticketList = new ArrayList<>();
    
    //synchronization Ticket
    public void addShowTicket(Ticket ticket) {
    	ticketList.add(ticket);
    	ticket.setShow(this);
    }
    public void removeShowTicket(Ticket ticket) {
    	ticketList.remove(ticket);
    	ticket.setShow(null);
    }
    
    //synchronization Show Seat
    public void addShowSeat(ShowSeat showSeat) {
    	showSeats.add(showSeat);
    	showSeat.setShow(this);
    }
    public void removeShowSeat(ShowSeat showSeat) {
    	showSeats.remove(showSeat);
    	showSeat.setShow(null);
    }
    
}
