package com.thinkbycode.restdemo.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thinkbycode.restdemo.dao.UserDAO;
import com.thinkbycode.restdemo.exception.UserNotFoundException;
import com.thinkbycode.restdemo.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	@GetMapping("/users")
	public List<User> getAll() {
		return userDao.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User userById(@PathVariable int id) {
		User user = userDao.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("User with id="+id+" not found.");
		}
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = userDao.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User deletedUser = userDao.deleteById(id);
		
		if(deletedUser == null) {
			throw new UserNotFoundException("User with id="+id+" not found.");
		}
	}
}
