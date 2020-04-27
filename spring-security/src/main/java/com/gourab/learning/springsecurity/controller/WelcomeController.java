package com.gourab.learning.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/")
	public String giveWelcomeMessage() {
		return "Welcome !";
	}
	
	@GetMapping("/admin")
	public String showAdminHome() {
		return "Welcome Admin !";
	}
	
	@GetMapping("/user")
	public String showUserHome() {
		return "Welcome User !";
	}
}
