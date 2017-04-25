package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Library;
import com.library.mappers.LibraryMapper;
import com.library.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private LibraryMapper libraryMapper;
	
	@RequestMapping(value = "/addBooks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addBooks(@RequestBody Library library) {
		return libraryService.addBooks(library);
	}

	@RequestMapping(value = "/updateBooks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateBooks(@RequestBody Library library) {
		return libraryService.updateBooks(library);
	}

	@RequestMapping(value = "/deleteBooks/{bookId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteBooks(@PathVariable Integer bookId) {
		return libraryService.deleteBooks(bookId);
	}
	
	@RequestMapping(value = "/getBooks/{bookId}", method = RequestMethod.GET)
	public List<Library> getBooks(@PathVariable Integer bookId) {
		return libraryMapper.mapLibrary(libraryService.getBooks());
	}

}
