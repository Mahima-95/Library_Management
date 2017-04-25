package com.library.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.library.dao.LibraryDao;
import com.library.extractor.Libraryextractor;
import com.library.pojo.Library;

@Repository
public class LibraryDaoImpl implements LibraryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String addBooks(Library library) {
		String response = null;
		if (!StringUtils.isEmpty(library)) {
			Object[] args = { library.getBookId(), library.getBookName(),
					library.getBookMessage() };
			int res = jdbcTemplate
					.update("INSERT INTO library(bookId, bookName, bookMessage) VALUES(?,?,?)",
							args);
			if (res > 0) {
				response = "Successfully added..!!!";
			} else {
				response = "Please try again later..!!!";
			}
		} else {
			response = "Please try again later..!!!";
		}
		return response;
	}

	@Override
	public String updateBooks(Library library) {
		String response = null;
		if (!StringUtils.isEmpty(library)) {
			Object[] args = { library.getBookName(), library.getBookMessage(),
					library.getBookId() };
			int res = jdbcTemplate
					.update("UPDATE library SET bookName =?, bookMessage=? WHERE bookId = ?",
							args);
			if (res > 0) {
				response = "Succesfully updated..!!!";
			}
		} else {
			response = "Please try again later..!!!";
		}
		return response;
	}

	@Override
	public String deleteBooks(int bookId) {
		String response = null;
		if (!StringUtils.isEmpty(bookId)) {
			Object[] args = { bookId };
			int res = jdbcTemplate.update("delete from library where bookId=?",
					args);
			if (res > 0) {
				response = "Succesfully deleted..!!!";
			}
		} else {
			response = "Please try again later..!!!";
		}
		return response;
	}

	@Override
	public List<Library> getBooks() {

		List<Library> res = jdbcTemplate.query("select * from library",
				new Libraryextractor());
		return res;
	}

}
