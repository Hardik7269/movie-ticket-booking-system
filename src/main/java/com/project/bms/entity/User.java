package com.project.bms.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {
	
	@Id
	private Long uId;
	private String uname;
	private int age;
	
	private String email;
	private String gender;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@JsonManagedReference("user-tickets")
	private List<Ticket> tikcets = new ArrayList<>();
	
	//syncronize the user with tickets
	public void addUserTicket(Ticket ticket) {
		tikcets.add(ticket);
		ticket.setUser(this);
	}
	public void removeUserTicket(Ticket ticket) {
		tikcets.add(ticket);
		ticket.setUser(this);
	}
}
