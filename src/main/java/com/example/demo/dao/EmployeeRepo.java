package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	Employee findById(long id);
	
	Optional<Employee> findByLastName(String lastName);
	Boolean existsByLastName(String lastName);
	Boolean existsByEmail(String email);
}
