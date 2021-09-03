package com.thinkbycode.restdemo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/greet")
	public String greet() {
		return "Hello World!!";
	}
	
	@GetMapping("/greet/byName/{name}")
	public String greetByName(@PathVariable String name) {
		return "Hey "+name;
	}
}
