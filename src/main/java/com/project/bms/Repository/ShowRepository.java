package com.project.bms.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer>{
	public Optional<Show> findByTimeAndDateAndTheaterIdAndMovieId(Time time , Date date, Integer theaterId, Integer movieId);
}
