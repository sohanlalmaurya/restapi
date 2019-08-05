package com.bcs.restapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.restapi.aop.errors.BadRequestAlertException;
import com.bcs.restapi.model.BooksModel;
import com.bcs.restapi.service.BooksService;

@RestController
@RequestMapping("/api")
public class BooksController {

	@Autowired
	private BooksService booksService;

	/**
	 * @description for book upload
	 * @param file
	 * @return
	 */
	@PostMapping(value = "/books/upload")
	public ResponseEntity<Map<String, Object>> uploadBook(@RequestParam("file") MultipartFile file) {
		if (Objects.isNull(file))
			throw new BadRequestAlertException("Emply file can not uploaded", "bookUpload", "uloadUrl");
		Map<String, Object> map = new HashMap<>();
		map.put("uploadPath", booksService.uploadBook(file));
		return ResponseEntity.ok(map);
	}

	/**
	 * POST /books : Create a new books.
	 *
	 * @param model the BooksModel to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         BooksModel
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/books")
	public ResponseEntity<BooksModel> createBook(@Valid @RequestBody BooksModel model) throws URISyntaxException {
		BooksModel result = booksService.update(model);
		return ResponseEntity.created(new URI("/api/books/" + result.getId())).body(result);
	}

	/**
	 * PUT /books : Updates an existing books.
	 *
	 * @param model the BooksModel to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         BooksModel, or with status 400 (Bad Request) if the BooksModel is not
	 *         valid, or with status 500 (Internal Server Error) if the BooksModel
	 *         couldn't be updated
	 * @throws Exception
	 */
	@PutMapping("/books")
	public ResponseEntity<BooksModel> updateBook(@Valid @RequestBody BooksModel model) {
		if (Objects.isNull(model.getId())) {
			throw new BadRequestAlertException("Does not update without 'id' ", "booksModel", "update");
		}
		return ResponseEntity.ok().body(booksService.save(model));
	}

	/**
	 * GET /books : get all the books.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of books in body
	 * 
	 *         Note:- call below api
	 *         localhost:8080/api/books?page=0&size=3&q=search=sohan,price=34.23
	 */
	@GetMapping("/books")
	public ResponseEntity<Page<BooksModel>> getAllBooks(Pageable pageable,
			@RequestParam(value = "q", required = false) String q) {
		return ResponseEntity.ok(booksService.findAll(pageable, q));
	}

	/**
	 * DELETE /books/:id : delete the "id" books.
	 * 
	 * @param id the id of the books
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		booksService.delete(id);
		return ResponseEntity.ok().build();
	}
}
