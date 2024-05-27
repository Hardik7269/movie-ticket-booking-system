package com.project.bms.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonManagedReference("theater-seats")
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonManagedReference("theater-shows")
    private List<Show> showList = new ArrayList<>();
    
    //sync theater with theaterSeats
    public void addTheaterTheaterSeat(TheaterSeat theaterSeat) {
    	theaterSeatList.add(theaterSeat);
    	theaterSeat.setTheater(this);
    }
    public void removeTheaterTheaterSeat(TheaterSeat theaterSeat) {
    	theaterSeatList.add(theaterSeat);
    	theaterSeat.setTheater(this);
    }
    
    //sync Theater with Shows
    public void addTheaterShow(Show show) {
    	showList.add(show);
    	show.setTheater(this);
    }
    public void removeTheaterShow(Show show) {
    	showList.remove(show);
    	show.setTheater(null);
    }
}
