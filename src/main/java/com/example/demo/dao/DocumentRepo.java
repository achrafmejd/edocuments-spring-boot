package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Document;

public interface DocumentRepo extends JpaRepository<Document, Long> {
	/* Get all document receibed by the user identified by the id passed to the function */
	List<Document> findAllByIdRecipient(int id);
	/* Get all document send by the user identified by the id passed to the function */
	@Query("from Document where employee_id_emp = ?1")
	List<Document> findAllByEmployee(int id);
	
	Document findById(long id);
	
	
 
}
