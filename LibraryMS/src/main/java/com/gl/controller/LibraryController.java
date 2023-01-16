package com.gl.controller;

import java.util.HashSet;
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
import org.springframework.web.client.RestTemplate;

import com.gl.dto.BookDTO;
import com.gl.dto.LibraryDTO;
import com.gl.exception.LibraryException;
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

	@GetMapping("/{id}")
	public ResponseEntity<LibraryDTO> getLibraryDetails(@PathVariable Integer id)throws LibraryException
	{
		LibraryDTO libraryDTO=service.getLibraryDetails(id);
		// Communicate to Book Library
		
		RestTemplate template=new RestTemplate();
		String _apiURL="http://localhost:8200/books/{libraryId}";
		ResponseEntity<Set> response=template.getForEntity(_apiURL,Set.class,id);
		Set<BookDTO> books=response.getBody();
		libraryDTO.setBooks(books);
		return new ResponseEntity<>(libraryDTO,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Integer id, @RequestBody LibraryDTO libraryDTO) throws LibraryException
	{
		LibraryDTO updated=service.updateLibrary(id, libraryDTO);
		
		RestTemplate template=new RestTemplate();
		String _apiURL="http://localhost:8200/books/{id}";
		Set<BookDTO> books=libraryDTO.getBooks();
		for(BookDTO b:books)
		{
			template.put(_apiURL, b, b.getId());
		}	
		updated.setBooks(books);
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLibraryDetails(@PathVariable Integer id) throws LibraryException
	{
		RestTemplate template=new RestTemplate();
		String _apiURL="http://localhost:8200/books/{libraryId}";
		
		ResponseEntity<Set> response=template.getForEntity(_apiURL,Set.class,id);
		Set<BookDTO> books=response.getBody();
		System.out.println(books);
		
		String deleteURL="http://localhost:8200/books/";
		for(BookDTO b:books)
		{
			deleteURL=deleteURL+b.getId();
			template.delete(deleteURL);
		}
		
		String res=service.deleteLibrary(id);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
