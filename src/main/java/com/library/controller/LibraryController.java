package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.pojo.Library;
import com.library.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(value ="/addBooks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addBooks(@RequestBody Library library) {
		return libraryService.addBooks(library);
	}

}
