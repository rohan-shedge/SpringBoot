package com.rohan.config;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.rohan.config.dao.UserDao;
import com.rohan.config.exception.UserNotFoundException;
import com.rohan.model.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	public EntityModel<User> fineOne(@PathVariable int id) { 
		Optional<User> user =  userDao.findOne(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}   	
		User user1 = user.get();
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
        EntityModel<User> entityModel = EntityModel.of(user1);
        Link link= WebMvcLinkBuilder.linkTo(
                methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        entityModel.add(link);
        return entityModel;
	}

	//create specific User
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createOne(@Valid @RequestBody User user) { 
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
