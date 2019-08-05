package com.bcs.restapi.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;

public class BooksSpecificationBuilder {

	private final List<SearchCriteria> params;

	public BooksSpecificationBuilder() {
		params = new ArrayList<>();
	}

	public BooksSpecificationBuilder with(String key, String operation, Object value) {
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Specification build() {
		if (params.isEmpty()) {
			return null;
		}
		List<Specification> specs = new ArrayList<>();
		params.forEach(param -> specs.add(new BooksSpecification(param)));
		Specification result = specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specification.where(result).and(specs.get(i));
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Specification getSpecification(String search) {

		Pattern pattern = Pattern.compile(SearchCriteria.SEARCHING_PATTERN);
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			this.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		return this.build();
	}
	}
