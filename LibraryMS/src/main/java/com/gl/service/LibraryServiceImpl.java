package com.gl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dto.LibraryDTO;
import com.gl.entity.Library;
import com.gl.exception.LibraryException;
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
	@Override
	public LibraryDTO getLibraryDetails(int id) throws LibraryException {
		Optional<Library> op=repository.findById(id);
		Library l=op.orElseThrow(()->new LibraryException("Library with id "+id+" does not exists."));
		return this.getLibraryDTO(l);
	}
	
	@Override
	public LibraryDTO updateLibrary(int id, LibraryDTO libraryDTO) throws LibraryException {
		Optional<Library> op=repository.findById(id);
		Library l=op.orElseThrow(()->new LibraryException("Library with id "+id+" does not exists."));
		
		l.setName(libraryDTO.getName());
		l.setAddress(libraryDTO.getAddress());
		repository.save(l);
		return this.getLibraryDTO(l);
	}
	
	@Override
	public String deleteLibrary(int id) throws LibraryException {
		repository.deleteById(id);
		return "deleted";
	}
	@Override
	public List<LibraryDTO> getAllLibrary() {
		Iterable<Library> itr=repository.findAll();
		List<LibraryDTO> libraries=new ArrayList<LibraryDTO>();
				itr.forEach((l)->{					
					libraries.add(this.getLibraryDTO(l));
		});
		return libraries;
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
