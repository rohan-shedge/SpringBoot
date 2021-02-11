package com.rohan.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

	private Integer id;

	private String name;

	private Date birthDate;

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public boolean equals(Object o) {
		User user = (User)o;
		return (this.name == user.name && this.birthDate == user.birthDate); 
	}
}

