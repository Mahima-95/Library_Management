package com.library.service;

import java.util.List;

import com.library.entity.Library;

public interface LibraryService {

	public String addBooks(Library library);

	public String updateBooks(Library library);

	public String deleteBooks(int bookId);

	public List<Library> getBooks();

}
