package com.cowcow.demo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class ArticleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long inr ;
	
	@Column
	private String filename;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name=" content", columnDefinition="CLOB", nullable=true) 
	private String content;

	public Long getInr() {
		return inr;
	}

	public void setInr(Long inr) {
		this.inr = inr;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
