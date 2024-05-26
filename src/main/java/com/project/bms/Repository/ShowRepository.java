package com.project.bms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bms.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer>{

}
