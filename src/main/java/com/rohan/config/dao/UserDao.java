package com.rohan.config.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rohan.model.User;

@Repository
public class UserDao {
	
	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public Optional<User> findOne(int id) {
		return users.stream()
		     .filter(x -> x.getId() == id)
		     .findFirst();
	}
	
	public Optional<User> deleteById(int id) {

		//only removal , return boolean
		//users.removeIf(x -> x.getId() ==id);
		users.stream()
		     .filter(x -> x.getId() ==id)
		     .map(p -> {
		    	 users.remove(p);
		    	 return p;
		     });
		return null;
	}
}