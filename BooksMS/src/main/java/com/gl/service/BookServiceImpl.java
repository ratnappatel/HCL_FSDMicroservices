package com.gl.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.BookDTO;
import com.gl.entity.Book;
import com.gl.exception.BookException;
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
	
	@Override
	public BookDTO getBookDetails(int id)throws BookException {
		Optional<Book> op=repository.findById(id);
		Book b=op.orElseThrow(()->new BookException("Book with given id does not exists.."));
		return this.getBookDTO(b);
	}
	
	
	@Override
	public BookDTO updateBookDetails(int id, BookDTO bookDTO) throws BookException {
	
		Optional<Book> op=repository.findById(id);
		Book b=op.orElseThrow(()->new BookException("Book with "+id+" Not Found"));
		b.setTitle(bookDTO.getTitle());
		b.setPrice(bookDTO.getPrice());
		
		repository.save(b);
		return this.getBookDTO(b);
	}

	@Override
	public String deleteBookDetails(int id) {
		repository.deleteById(id);
		
		return "Deleted";
	}
	
	

	@Override
	public List<BookDTO> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Set<BookDTO> getBookByLibraryId(int libraryId) {
		List<Book> books=repository.findByLibraryId(libraryId);
		Set<BookDTO> bookDTOs=new HashSet<>();
		books.forEach((book)->{
			bookDTOs.add(this.getBookDTO(book));
		});
		
		return bookDTOs;
	}
}
