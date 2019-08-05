package com.bcs.restapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BooksModel {

	private Long id;

	@NotNull
	@Size(max = 100)
	private String name;

	@NotNull
	@Size(max = 100)
	private String author;

	@NotNull
	private Double price;

	@NotNull
	private Integer year;
	
	@NotNull
	@Size(max = 250)
	private String uploadUrl;

	public BooksModel() {
	}

	public BooksModel(Long id, String name, String author, Double price, Integer year, String uploadUrl) {
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
}
