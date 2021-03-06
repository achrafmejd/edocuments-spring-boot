package com.example.demo.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.EmployeeRepo;
import com.example.demo.model.Employee;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	EmployeeRepo empRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = empRepo.findByLastName(username)
				.orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(emp);
	}

}
