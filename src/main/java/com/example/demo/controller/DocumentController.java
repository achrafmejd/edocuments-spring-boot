package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dao.DocumentRepo;
import com.example.demo.dao.EmployeeRepo;
import com.example.demo.message.ResponseDocument;
import com.example.demo.message.ResponseMessage;
import com.example.demo.model.Document;
import com.example.demo.model.Employee;
import com.example.demo.service.DocumentStorageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DocumentController {
	@Autowired
	DocumentRepo docRepo;
	@Autowired
	private DocumentStorageService storageService;
	@Autowired
	EmployeeRepo empRepo;
	
	/* Get All documents 
	 * => For admin */
	/*@RequestMapping("/documents")
	@ResponseBody
	public List<Document> allDocuments(){
		return docRepo.findAll();
	}*/
	
	
	@GetMapping("/documents")
	public List<ResponseDocument> getListFiles(){
		
		List<ResponseDocument> files = storageService.getAllFiles().map(
				dbFile -> {
				      String fileDownloadUri = ServletUriComponentsBuilder
				          .fromCurrentContextPath()
				          .path("/documents/")
				          .path(String.valueOf(dbFile.getIdDoc()))
				          .toUriString();
				      return new 	ResponseDocument(
					    		 dbFile.getDocName(),
					    		 fileDownloadUri,
					    		 dbFile.getType(),
					    		 dbFile.getData().length,
					    		 dbFile.getEmployee().getFirstName(),
					    		 dbFile.getEmployee().getLastName(),
					    		 dbFile.getDate(),
					    		 empRepo.findById(dbFile.getIdRecipient()).getLastName(),
					    		 empRepo.findById(dbFile.getIdRecipient()).getFirstName()
					    	);
				})
				.collect(Collectors.toList());
		return files;
		
	}
	
	/* Get A specific document by id */
	/*@RequestMapping("/document/{id}")
	@ResponseBody
	public Optional<Document> document(@PathVariable("id") int id){
		System.out.println(docRepo.findById(id));
		return docRepo.findById(id);
	}*/
	
	/* Get send document based on id (can be changed )*/
	@RequestMapping("document-sent/{id}")
	/* The id here represents the user */
	@ResponseBody
	public List<ResponseDocument> documentSentById(@PathVariable("id") int id){
		List<ResponseDocument> files = (List<ResponseDocument>) docRepo.findAllByEmployee(id).stream().map(
				dbFile -> {
					String fileDownloadUri = ServletUriComponentsBuilder
					          .fromCurrentContextPath()
					          .path("/documents/")
					          .path(String.valueOf(dbFile.getIdDoc()))
					          .toUriString();
					 return new 	ResponseDocument(
				    		 dbFile.getDocName(),
				    		 fileDownloadUri,
				    		 dbFile.getType(),
				    		 dbFile.getData().length,
				    		 dbFile.getEmployee().getFirstName(),
				    		 dbFile.getEmployee().getLastName(),
				    		 dbFile.getDate(),
				    		 empRepo.findById(dbFile.getIdRecipient()).getLastName(),
				    		 empRepo.findById(dbFile.getIdRecipient()).getFirstName()
				    	);
				}
				).collect(Collectors.toList());
		return files;
		
	}
	/* public List<Document> documentSentById(@PathVariable("id") int id){
		return docRepo.findAllByEmployee(id);
	}*/
	
	
	/* Get received document based on id (can be changed ) */
	@RequestMapping("document-received/{id}")
	/* The id here represents the user */
	@ResponseBody
	public List<ResponseDocument> documentReceivedById(@PathVariable("id") int id){
		List<ResponseDocument> files = (List<ResponseDocument>) docRepo.findAllByIdRecipient(id).stream().map(
				dbFile -> {
					String fileDownloadUri = ServletUriComponentsBuilder
					          .fromCurrentContextPath()
					          .path("/documents/")
					          .path(String.valueOf(dbFile.getIdDoc()))
					          .toUriString();
					 return new 	ResponseDocument(
				    		 dbFile.getDocName(),
				    		 fileDownloadUri,
				    		 dbFile.getType(),
				    		 dbFile.getData().length,
				    		 dbFile.getEmployee().getFirstName(),
				    		 dbFile.getEmployee().getLastName(),
				    		 dbFile.getDate(),
				    		 empRepo.findById(dbFile.getIdRecipient()).getLastName(),
				    		 empRepo.findById(dbFile.getIdRecipient()).getFirstName()
				    	);
				}
				).collect(Collectors.toList());
		return files;
		
	}
	/*public List<Document> documentReceivedById(@PathVariable("id") int id){
		return docRepo.findAllByIdRecipient(id);
	}*/
	
	/* Send a document to an employee */
	@PostMapping(value="/sendDocument", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ResponseMessage> sendDocument(@RequestPart("idSender") String idSender, 
							  @RequestPart("file") MultipartFile file,
							  @RequestPart("comment") String comment,
							  @RequestPart("docName") String docName,
							  @RequestPart("idRecipient") String idRecipient ){
		String message = "";
		/* Get current date */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		
		try {
			
			storageService.store(comment, dtf.format(now), Integer.parseInt(idRecipient), empRepo.findById(Long.parseLong(idSender)),  file);
		    message = "Uploaded the file successfully: " + file.getOriginalFilename();
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch(Exception e) {
			  System.out.println(e);
		      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	}
	}
	
	/* Update a document */
	@PutMapping("/document")
	public Document saveOrUpdateDocument(@RequestBody Document doc) {
		docRepo.save(doc);
		return doc;
	}
	
	/* Delete a document */
	@RequestMapping(value = "/document/{id}", method = RequestMethod.DELETE)
	public String deleteDocument(@PathVariable("id") int id) {
		/*Document doc = docRepo.getById(id);*/
		docRepo.delete(docRepo.findById(id));
		/*docRepo.delete(doc);*/
		return "deleted";
	}
	
	
	@GetMapping("/documents/{id}")
	public ResponseEntity<ByteArrayResource> getFile(@PathVariable long id) {
	  Document fileDB = storageService.getFile(id);
	  return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(fileDB.getType()))
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getDocName() + "\"")
              .body(new ByteArrayResource(fileDB.getData()));
	}
	  
}