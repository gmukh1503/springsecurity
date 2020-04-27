package com.gourab.learning.springsecurityjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	
	@GetMapping("/")
	public String home() {
		return "Welcome !";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin !";
	}
	
	@GetMapping("/user")
	public String user() {
		return "Welcome User !";
	}

}
