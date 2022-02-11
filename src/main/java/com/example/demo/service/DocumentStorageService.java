package com.example.demo.service;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.DocumentRepo;
import com.example.demo.model.Document;
import com.example.demo.model.Employee;


@Service
public class DocumentStorageService{
	@Autowired
	private DocumentRepo docRepo;
	
	public Document store(String comment, String date, int idRecipient, Employee emp, MultipartFile file) throws IOException{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Document Document = new Document(fileName, file.getBytes(), file.getContentType(), comment, date, idRecipient, emp);
		return docRepo.save(Document);
	}
	
	public Document getFile(long id) {
		return docRepo.findById(id);
	}
	
	public Stream<Document> getAllFiles(){
		return docRepo.findAll().stream();
	}
	
	
	
}
