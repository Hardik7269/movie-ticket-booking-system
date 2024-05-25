package com.project.bms.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	private long uId;
	private String uname;
	private int age;
	
	private String email;
	private String gender;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Ticket> tikcets = new ArrayList<>();
}
