package com.gourab.learning.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gourab.learning.springsecurityjwt.model.AuthorizationRequest;
import com.gourab.learning.springsecurityjwt.model.AuthorizationResponse;
import com.gourab.learning.springsecurityjwt.service.MyUserDetailsService;
import com.gourab.learning.springsecurityjwt.util.JWTUtil;

@RestController
public class HelloResource {

	@Autowired
	private AuthenticationManager authMangr;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@GetMapping("/hello")	
	public String sayHello() {
		return "Hello User, U r welcome !";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthorizationResponse> createAuthenticationToken(@RequestBody AuthorizationRequest request) throws Exception{
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
		try {
			authMangr.authenticate(authentication);
		}catch(AuthenticationException e) {
			throw new Exception("Invalid Credentials.");
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthorizationResponse(jwt));
	}
}
