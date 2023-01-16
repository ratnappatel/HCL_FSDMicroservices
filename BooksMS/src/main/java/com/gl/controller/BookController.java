package com.gl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.dto.BookDTO;
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

}
