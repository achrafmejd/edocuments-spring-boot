package com.example.demo.message;


public class ResponseDocument {
	private String docName;
	private String url;
	private String type;
	private long size;
	private String senderFirstName;
	private String senderLastName;
	private String date;
	
	private String receiverLastName;
	private String receiverFirstName;
	
	public ResponseDocument(String docName, String url, String type, long size, String senderFirstName,
			String senderLastName, String date, String receiverLastName, String receiverFirstName) {
		super();
		this.docName = docName;
		this.url = url;
		this.type = type;
		this.size = size;
		this.senderFirstName = senderFirstName;
		this.senderLastName = senderLastName;
		this.date = date;
		this.receiverLastName = receiverLastName;
		this.receiverFirstName = receiverFirstName;
	}

	

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getSenderFirstName() {
		return senderFirstName;
	}

	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}

	public String getSenderLastName() {
		return senderLastName;
	}

	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getReceiverLastName() {
		return receiverLastName;
	}

	public void setReceiverLastName(String receiverLastName) {
		this.receiverLastName = receiverLastName;
	}

	public String getReceiverFirstName() {
		return receiverFirstName;
	}

	public void setReceiverFirstName(String receiverFirstName) {
		this.receiverFirstName = receiverFirstName;
	}
	
	
	
	
	/*
	
	public ResponseDocument() {
		super();
	}

	public ResponseDocument(String docName, String url, String type, long size) {
		this.docName = docName;
		this.url = url;
		this.type = type;
		this.size = size;
	}
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	} */
	
}
