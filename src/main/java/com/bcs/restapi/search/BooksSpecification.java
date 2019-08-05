/**
 * 
 */
package com.bcs.restapi.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bcs.restapi.entity.BooksEntity;

/**
 * @author Baiskhi chuahan
 *
 *         this is generic JPA Specification for use filter data from database
 * @param <T>
 */

public class BooksSpecification implements Specification<BooksEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SearchCriteria criteria;

	public BooksSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<BooksEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (SearchCriteria.SORTING.equalsIgnoreCase(criteria.getOperation())) {
			if (SearchCriteria.DESC.equalsIgnoreCase(String.valueOf(criteria.getValue()))) {
				query.orderBy(criteriaBuilder.desc(root.get(criteria.getKey())));
			} else if (SearchCriteria.ASC.equalsIgnoreCase(String.valueOf(criteria.getValue()))) {
				query.orderBy(criteriaBuilder.asc(root.get(criteria.getKey())));
			}
		} else {
			query.orderBy(criteriaBuilder.desc(root.get("createdOn")));
		}
		if (SearchCriteria.CONTAINS.equalsIgnoreCase(criteria.getOperation())) {
			if ("search".equals(criteria.getKey())) {
				return criteriaBuilder.or(
						criteriaBuilder.like(root.get("author"),
								SearchCriteria.PERCENTAGE + criteria.getValue() + SearchCriteria.PERCENTAGE),
						criteriaBuilder.like(root.get("name"),
								SearchCriteria.PERCENTAGE + criteria.getValue() + SearchCriteria.PERCENTAGE));
			} else {
				return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}

}
