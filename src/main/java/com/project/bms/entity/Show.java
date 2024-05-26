package com.project.bms.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showId;
    
    private Time time;
    private Date date;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn
    private Movie movie;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn
    private Theater theater;
    
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ShowSeat> showSeats = new ArrayList<>();
    
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> ticketList = new ArrayList<>();
}
