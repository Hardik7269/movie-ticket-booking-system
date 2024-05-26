package com.project.bms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
