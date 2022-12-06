package com.handson.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@GetMapping(name = "/user")
   public ResponseEntity<String> hello(){
	   return new ResponseEntity<String>("welcome", HttpStatus.OK);
   }
}
