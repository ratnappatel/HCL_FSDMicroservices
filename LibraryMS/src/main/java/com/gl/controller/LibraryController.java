package com.gl.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gl.dto.BookDTO;
import com.gl.dto.LibraryDTO;
import com.gl.service.LibraryService;

@RestController
@RequestMapping(path = "/libraries")
public class LibraryController {
	
	@Autowired
	LibraryService service;
	
	@PostMapping("/")
	public ResponseEntity<LibraryDTO> addLibrary(@RequestBody LibraryDTO libraryDTO)
	{
		LibraryDTO addedlibraryDTO=service.addLibrary(libraryDTO);
		int lid=addedlibraryDTO.getId();
		
		RestTemplate template=new RestTemplate();
		String _apiURL="http://localhost:8200/books/";
		Set<BookDTO> books=new HashSet<BookDTO>();
		for(BookDTO b:libraryDTO.getBooks())
		{
			
			b.setLibraryId(lid);
			BookDTO addedBook=template.postForEntity(_apiURL,b,BookDTO.class).getBody();
			books.add(addedBook);
		}
		addedlibraryDTO.setBooks(books);
		
		return new ResponseEntity<>(addedlibraryDTO,HttpStatus.OK);
	}

}
