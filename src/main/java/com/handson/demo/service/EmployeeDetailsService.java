package com.handson.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.handson.demo.models.Employee;
import com.handson.demo.models.EmployeeDetails;
import com.handson.demo.repository.EmployeeRepository;

@Service
public class EmployeeDetailsService implements UserDetailsService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findByUsername(username);
		System.out.println("##" +  employee);
		return new EmployeeDetails(employee);
		
	}

}
