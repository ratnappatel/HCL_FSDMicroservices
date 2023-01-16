package com.gl.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gl.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	public List<Book> findByLibraryId(int libraryId);
}
