package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.BookDTO;
import com.gl.entity.Book;
import com.gl.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository repository;
	@Override
	public BookDTO addNewBook(BookDTO bookDTO) {
		Book book=repository.save(this.getBook(bookDTO));
		
		return this.getBookDTO(book);
	}
	
	public BookDTO getBookDTO(Book book)
	{
		BookDTO bookDTO=new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setPrice(book.getPrice());
		bookDTO.setLibraryId(book.getLibraryId());
		return bookDTO;
		
	}
	public Book getBook(BookDTO bookDTO)
	{
		Book book=new Book();
		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setPrice(bookDTO.getPrice());
		book.setLibraryId(bookDTO.getLibraryId());
		return book;		
	}
	

}
