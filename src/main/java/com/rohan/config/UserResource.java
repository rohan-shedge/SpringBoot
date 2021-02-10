package com.rohan.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.config.dao.UserDao;
import com.rohan.model.User;

@RestController
public class UserResource {

	@Autowired
	private UserDao userDao;
	
	//retrieve All Users
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() { 
		return userDao.findAll();
	}
	
	//retrieve specific User
	@GetMapping(path = "/users/{id}")
	public Optional<User> fineOne(@PathVariable int id) { 
		return userDao.findOne(id);
	}
}
