package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.LibraryDao;
import com.library.pojo.Library;
import com.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	@Override
	public String addBooks(Library library) {
		return libraryDao.addBooks(library);
	}

	@Override
	public String updateBooks(Library library) {
		return libraryDao.updateBooks(library);
	}

	@Override
	public String deleteBooks(int bookId) {
		return libraryDao.deleteBooks(bookId);
	}

	@Override
	public List<Library> getBooks() {
		return libraryDao.getBooks();
	}

}
