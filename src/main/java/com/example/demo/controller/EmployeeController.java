package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.EmployeeRepo;
import com.example.demo.model.Employee;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EmployeeController {
	@Autowired 
	EmployeeRepo empRepo;
	
	/* For test */
	@RequestMapping("/test")
	public String hello(){
		return "Hello";
	}
	/* Get all employees : used to list all the employees to the admin */
	@RequestMapping("/employees")
	@ResponseBody
	public List<Employee> allEmployees() {
		return empRepo.findAll();
	}
	
	/* can be used to Get a specific employee profile */
	@RequestMapping("/employee/{id}")
	@ResponseBody
	public Employee employee(@PathVariable("id") int id) {
		return empRepo.findById(id);
	}
	
	/* add an employee */
	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee emp) {
		empRepo.save(emp);
		return emp;
	}
	
	/* update employee */
	@PutMapping("/employee")
	public Employee saveOrUpdateEmployee(@RequestBody Employee emp) {
		empRepo.save(emp);
		return emp;
	}
	/* delete an employee */
	/* ! the function needs to handle exception 
	 * => a user that had some document send (foreign key in document) cannot be deleted */
	@RequestMapping(value="/employee/{id}", method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("id") long id) {
		
		Employee emp = empRepo.getById(id);
		empRepo.delete(emp);
		return "deleted";
	}
}
