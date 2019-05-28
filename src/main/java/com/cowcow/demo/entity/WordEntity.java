package com.cowcow.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "word")
public class WordEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//自增
	private Long inr;
	@Column
	private String filename;
	@Column
	private String text;
	@Column
	private Long count;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
