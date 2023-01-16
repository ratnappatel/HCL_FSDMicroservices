package com.gl.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable{

	private int id;
	private String title;
	private float price;
	private int libraryId;
}
