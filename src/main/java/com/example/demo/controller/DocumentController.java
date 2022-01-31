package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DocumentRepo;
import com.example.demo.model.Document;

@RestController
public class DocumentController {
	@Autowired
	DocumentRepo docRepo;
	
	/* Get All documents 
	 * => For admin */
	@RequestMapping("/documents")
	@ResponseBody
	public List<Document> allDocuments(){
		return docRepo.findAll();
	}
	
	
	/* Get A specific document by id */
	@RequestMapping("/document/{id}")
	@ResponseBody
	public Optional<Document> document(@PathVariable("id") int id){
		System.out.println(docRepo.findById(id));
		return docRepo.findById(id);
	}
	
	/* Get send document based on id (can be changed )*/
	@RequestMapping("document-sent/{id}")
	/* The id here represents the user */
	@ResponseBody
	public List<Document> documentSentById(@PathVariable("id") int id){
		return docRepo.findAllByEmployee(id);
	}
	
	/* Get received document based on id (can be changed ) */
	@RequestMapping("document-received/{id}")
	/* The id here represents the user */
	@ResponseBody
	public List<Document> documentReceivedById(@PathVariable("id") int id){
		return docRepo.findAllByIdRecipient(id);
	}
	 
	/* Send a document to an employee */
	@PostMapping("/sendDocument")
	public Document sendDocument(@RequestBody Document doc) {
		docRepo.save(doc);
		return doc;
	}
	
	/* Update a document */
	@PutMapping("/document")
	public Document saveOrUpdateDocument(@RequestBody Document doc) {
		docRepo.save(doc);
		return doc;
	}
	
	/* Delete a document */
	@DeleteMapping("/document/{id}")
	public String deleteDocument(@PathVariable("id") int id) {
		Document doc = docRepo.getById(id);
		docRepo.delete(doc);
		return "deleted";
	}
	
}