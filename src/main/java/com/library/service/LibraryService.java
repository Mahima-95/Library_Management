package com.library.service;

import java.util.List;

import com.library.entity.Library;
import com.library.enums.LibraryEnum;

public interface LibraryService {
	
	public LibraryEnum getEnum();

	public String addBooks(Library library);

	public String updateBooks(Library library);

	public String deleteBooks(int bookId);

	public List<Library> getBooks();

}
