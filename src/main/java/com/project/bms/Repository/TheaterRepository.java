package com.project.bms.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer>{
	public Optional<Theater> findByName(String name);
}
