package com.project.bms.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	public Optional<Movie> findByMovieName(String name);
}
