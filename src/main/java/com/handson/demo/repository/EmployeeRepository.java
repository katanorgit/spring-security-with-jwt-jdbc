package com.handson.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handson.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	        public Employee findByUsername(String username);

}
