package com.bcs.restapi.search;

public class SearchCriteria {

	// example: q=search=Sohan or q=price=20
	public static final String SEARCHING_PATTERN = "(\\w.+?)(:|=|<|>|\\[\\])((?:\\[.*?\\])|[^,]*)";
	public static final String CONTAINS = "=";
	public static final String PERCENTAGE = "%";
	public static final String SORTING = ":";
	public static final String DESC = "desc";
	public static final String ASC = "asc";

	private String key;
	private String operation;
	private Object value;

	public SearchCriteria() {
		super();
	}

	public SearchCriteria(String key, String operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}