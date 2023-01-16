package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.LibraryDTO;
import com.gl.entity.Library;
import com.gl.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Autowired
	LibraryRepository repository;
	
	@Override
	public LibraryDTO addLibrary(LibraryDTO libraryDTO) {
		Library savedLibrary=repository.save(this.getLibrary(libraryDTO));
		return this.getLibraryDTO(savedLibrary);
	}
	
	// Utility mathods to convert DTO ==> Entity and Vice versa....
	public Library getLibrary(LibraryDTO libraryDTO)
	{
		Library library=new Library();
		library.setId(libraryDTO.getId());
		library.setName(libraryDTO.getName());
		library.setAddress(libraryDTO.getAddress());
		return library;
	}
	
	public LibraryDTO getLibraryDTO(Library library)
	{
		LibraryDTO libraryDTO=new LibraryDTO();
		libraryDTO.setId(library.getId());
		libraryDTO.setName(library.getName());
		libraryDTO.setAddress(library.getAddress());
		return libraryDTO;
	}

}
