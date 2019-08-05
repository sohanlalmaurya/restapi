package com.bcs.restapi.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class BooksEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Size(max = 100)
	@Column(name = "author", length = 100, nullable = false)
	private String author;

	@NotNull
	@Column(name = "price", length = 50, nullable = false)
	private Double price;

	@NotNull
	@Column(name = "year", length = 4, nullable = false)
	private Integer year;
	
	@Size(max = 250)
	@Column(name = "upload_url", length = 250, nullable = false)
	private String uploadUrl;

	@Column(name = "created_on")
	private Instant createdOn;

	@Column(name = "modified_on")
	private Instant modifiedOn = Instant.now();

	public BooksEntity() {
	}

	public BooksEntity(Long id, String name, String author, Double price, Integer year, String uploadUrl) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.year = year;
		this.uploadUrl = uploadUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public String getUploadUrl() {
		return uploadUrl;
	}
	
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public void setModifiedOn(Instant modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Instant getModifiedOn() {
		return modifiedOn;
	}
}
