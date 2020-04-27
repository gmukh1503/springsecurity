package com.gourab.learning.springsecurityldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	@GetMapping("/")
	public String showHomePage() {
		return "Welcome To Home !!";
	}
}
