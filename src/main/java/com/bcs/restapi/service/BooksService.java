package com.bcs.restapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.restapi.model.BooksModel;

public interface BooksService {
	
    /**
     * upload a books.
     *
     * @param file the book to upload
     * @return save file url
     */
	public String uploadBook(MultipartFile file);
	
    /**
     * Save a books.
     *
     * @param model the entity to save
     * @return the persisted entity
     */
	public BooksModel save(BooksModel model);
	
	 /**
     * Update a books.
     *
     * @param model the entity to update
     * @return the persisted entity
     */
	public BooksModel update(BooksModel model);
	
    /**
     * Get all the books.
     *
     * @param pageable the pagination information
     * @param search 
     * @return the list of entities
     */
    Page<BooksModel> findAll(Pageable pageable, String search);
	
    
	/**
	 * Delete the "id" books.
	 *
	 * @param id
	 *  the id of the entity
	 */
	public void delete(Long id);
	
}
