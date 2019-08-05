package com.bcs.restapi.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.restapi.entity.BooksEntity;
import com.bcs.restapi.model.BooksModel;
import com.bcs.restapi.repository.BooksRepository;
import com.bcs.restapi.search.BooksSpecificationBuilder;
import com.bcs.restapi.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {

	@Value("${upload.path}")
	private String uploadPath;
	
	@Autowired
	private BooksRepository booksRepository;
	
	@Override
	public String uploadBook(MultipartFile file) {

		File imagefolder = new File(uploadPath);
		if (!imagefolder.exists()) {
			imagefolder.mkdir();
		}
		String tempFile = uploadPath + File.separator + Math.random();
		File distributorFolder = new File(tempFile);
		if (!distributorFolder.exists()) {
			distributorFolder.mkdir();
		}
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(tempFile+ File.separator + file.getOriginalFilename());
			fo.write(file.getBytes());
		} catch ( IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return distributorFolder.getAbsolutePath();
	}

	@Override
	public BooksModel save(BooksModel model) {
		BooksEntity entity = toEntity(model);
		entity.setCreatedOn(Instant.now());
		return toModel(booksRepository.save(entity));
	}
	
	@Override
	public BooksModel update(BooksModel model) {
		return toModel(booksRepository.save(toEntity(model)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<BooksModel> findAll(Pageable pageable, String search) {
		Page<BooksEntity> pages = null;
		if (!StringUtils.isEmpty(search)) {
			Specification<BooksEntity> specification = new BooksSpecificationBuilder().getSpecification(search);
			pages = booksRepository.findAll(specification, pageable);
			if (Objects.nonNull(pages))
				return new PageImpl<BooksModel>(pages.get().map(entity -> toModel(entity)).collect(Collectors.toList()),
						pageable, pages.getTotalElements());
		} else {
			pages = booksRepository.findAll(pageable);
		}
		if (Objects.nonNull(pages))
			return new PageImpl<BooksModel>(pages.get().map(entity -> toModel(entity)).collect(Collectors.toList()),
					pageable, pages.getTotalElements());
		return null;
	}

	@Override
	public void delete(Long id) {
		booksRepository.deleteById(id);
	}
	
	private BooksEntity toEntity(BooksModel model) {
		if (Objects.nonNull(model))
			return new BooksEntity(model.getId(), model.getName(), model.getAuthor(), model.getPrice(), model.getYear(),
					model.getUploadUrl());
		return null;
	}
	
	private BooksModel toModel(BooksEntity entity) {
		if (Objects.nonNull(entity))
			return new BooksModel(entity.getId(), entity.getName(), entity.getAuthor(), entity.getPrice(), entity.getYear(),
					entity.getUploadUrl());
		return null;
	}
}
