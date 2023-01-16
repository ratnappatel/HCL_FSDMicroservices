package com.gl.service;

import java.util.List;
import java.util.Set;

import com.gl.dto.BookDTO;
import com.gl.exception.BookException;

public interface BookService {
	
	public BookDTO addNewBook(BookDTO bookDTO);
	
	public BookDTO getBookDetails(int id) throws BookException;
	public BookDTO updateBookDetails(int id, BookDTO bookDTO)throws BookException;
	public String deleteBookDetails(int id);
	public List<BookDTO> getAllBooks();
	
	
	public Set<BookDTO> getBookByLibraryId(int libraryId);

}
