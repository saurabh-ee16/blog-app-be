package com.blog.rest.blogapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.Long;

@Entity
@Table
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	private Date date;
	@Enumerated(EnumType.STRING)
	private BlogStatus postStatus;

	public Blog() {

	}

	public Blog(long id, String subject, String description, Date date, BlogStatus postStatus) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.date = date;
		this.postStatus = postStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BlogStatus getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(BlogStatus postStatus) {
		this.postStatus = postStatus;
	}

}
