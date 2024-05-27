package com.project.bms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String movieName;
    
    private LocalDate moviereleaseDate;
    
    private long duration;
    private String language;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference("movie-shows")
    private List<Show> shows = new ArrayList<>();
    
    //Synchronization Shows with moive
    public void addMovieShows(Show show) {
    	shows.add(show);
    	show.setMovie(this);
    }
    
    public void removeMovieShows(Show show) {
    	shows.remove(show);
    	show.setMovie(null);
    }
}
