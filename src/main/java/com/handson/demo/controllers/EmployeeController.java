package com.handson.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.handson.demo.service.EmployeeDetailsService;
import com.handson.demo.util.JWTRequest;
import com.handson.demo.util.JWTResponse;
import com.handson.demo.util.JWTUtility;

@RestController
public class EmployeeController {

	@Autowired
	JWTUtility jWTUtility;

	@Autowired
	EmployeeDetailsService employeeDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<>("welocme", HttpStatus.OK);
	}

	@PostMapping("/generateToken")
	public JWTResponse authenticate(@RequestBody JWTRequest Jwtrequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(Jwtrequest.getUsername(), Jwtrequest.getPassword()));
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("INVALID_CREDENTIALS", e);

		}
		UserDetails loadUserByUsername = employeeDetailsService.loadUserByUsername(Jwtrequest.getUsername());
		System.out.println("loadUserByUsername :: {} "+ loadUserByUsername);

		String generateToken = jWTUtility.generateToken(loadUserByUsername);
		return new JWTResponse(generateToken);
	}
}
