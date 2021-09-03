package com.thinkbycode.restdemo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.thinkbycode.restdemo.model.User;

@Component
public class UserDAO {
	private static List<User> users = new ArrayList<>();
	private static int count = 3;
			
	static {
		users.add(new User(1, "Mark", new Date()));
		users.add(new User(2, "Mike", new Date()));
		users.add(new User(3, "Michael", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
		return user.orElse(null);
	}

	public User deleteById(int id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(u.getId() == id) {
				it.remove();
				return u;
			}
		}
		return null;
	}
}
