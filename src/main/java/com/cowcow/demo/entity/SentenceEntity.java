package com.cowcow.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//设计有问题，表名和字段名不应该一致
@Entity
@Table(name = "sentence")
public class SentenceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long inr;
	
	@Column
	private String sentence;
	
	@Column
	private String filename;

	public Long getInr() {
		return inr;
	}

	public void setInr(Long inr) {
		this.inr = inr;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
