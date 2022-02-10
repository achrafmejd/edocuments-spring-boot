package com.example.demo.message;


public class ResponseDocument {
	private String docName;
	private String url;
	private String type;
	private long size;
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
	}
	
}
