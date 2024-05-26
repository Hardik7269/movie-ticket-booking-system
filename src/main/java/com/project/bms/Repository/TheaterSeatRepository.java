package com.project.bms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.TheaterSeat;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer>{

}
