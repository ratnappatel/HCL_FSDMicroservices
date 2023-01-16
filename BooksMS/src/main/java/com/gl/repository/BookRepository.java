package com.gl.repository;



import org.springframework.data.repository.CrudRepository;

import com.gl.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
