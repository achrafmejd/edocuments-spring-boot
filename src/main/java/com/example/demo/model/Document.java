package com.example.demo.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDoc;
	private String docName;
	@Lob
	private byte[] data;
	private String type;
	private String comment;
	private String date;
	private int idRecipient;
	@ManyToOne
	private Employee employee;
	/* Constructors */
	
	public Document() {
		super();
	}
	public Document(String docName, byte[] data, String type, String comment, String date, int idRecipient,
			Employee employee) {
		this.docName = docName;
		this.data = data;
		this.type = type;
		this.comment = comment;
		this.date = date;
		this.idRecipient = idRecipient;
		this.employee = employee;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/* Getters & Setters */
	public long getIdDoc() {
		return idDoc;
	}
	public void setIdDoc(long idDoc) {
		this.idDoc = idDoc;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdRecipient() {
		return idRecipient;
	}
	public void setIdRecipient(int idRecipient) {
		this.idRecipient = idRecipient;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
