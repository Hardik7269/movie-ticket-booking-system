package com.project.bms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.ShowSeat;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer>{
	public ShowSeat findBySeatNo(String seatNumber);
}
