package com.gl.dto;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {
	
	private int id;
	private String name;
	private String address;
	private Set<BookDTO> books;

}
