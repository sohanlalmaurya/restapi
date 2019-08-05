package com.bcs.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bcs.restapi.entity.BooksEntity;

@Repository
public interface BooksRepository extends  JpaRepository<BooksEntity, Long>, JpaSpecificationExecutor<BooksEntity> {

}
