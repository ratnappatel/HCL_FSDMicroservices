package com.gl.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dto.BookDTO;
import com.gl.exception.BookException;
import com.gl.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {
	
	@Autowired
	BookService service;
	
	@PostMapping("/")
	public ResponseEntity<BookDTO> addNewBook(@RequestBody BookDTO bookDTO)
	{
		bookDTO=service.addNewBook(bookDTO);
		return new ResponseEntity<>(bookDTO,HttpStatus.OK);
	}
	
	@GetMapping("/{libraryId}")
	public ResponseEntity<Set<BookDTO>> getBooksByLibraryId(@PathVariable Integer libraryId)
	{
		Set<BookDTO> books=service.getBookByLibraryId(libraryId);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookDTO> updateBookDetails(@PathVariable Integer id,@RequestBody BookDTO bookDTO)throws BookException
	{
		bookDTO=service.updateBookDetails(id, bookDTO);
		return new ResponseEntity<>(bookDTO,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBookDetails(@PathVariable Integer id)
	{
		String msg=service.deleteBookDetails(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}

}
