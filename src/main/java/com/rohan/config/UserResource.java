package com.rohan.config;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rohan.config.dao.UserDao;
import com.rohan.config.exception.UserNotFoundException;
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
		Optional<User> user =  userDao.findOne(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}   	
		return user;
	}
	
	//create specific User
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createOne(@RequestBody User user) { 
		User savedUser = userDao.createOne(user);
		URI location = ServletUriComponentsBuilder
		    .fromCurrentRequest()
		    .path("/{id}")
		    .buildAndExpand(savedUser.getId()).toUri();
		                          
		return ResponseEntity.created(location).build();
	}
	
	//Delete specific User
	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<Object> deleteOne(@PathVariable int userId) { 
		 userDao.deleteById(userId);
		 return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
