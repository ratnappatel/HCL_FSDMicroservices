package com.gl.service;

import java.util.List;

import com.gl.dto.LibraryDTO;
import com.gl.exception.LibraryException;

public interface LibraryService {
	
	public LibraryDTO addLibrary(LibraryDTO libraryDTO);
	public LibraryDTO getLibraryDetails(int id)throws LibraryException;
	public LibraryDTO updateLibrary(int id,LibraryDTO libraryDTO)throws LibraryException;
	public String deleteLibrary(int id)throws LibraryException;
	public List<LibraryDTO> getAllLibrary();

}
