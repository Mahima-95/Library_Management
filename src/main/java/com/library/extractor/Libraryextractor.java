package com.library.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.library.pojo.Library;

public class Libraryextractor implements ResultSetExtractor<List<Library>> {

	@Override
	public List<Library> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List<Library> libraries = new ArrayList<Library>();
		Library library = null;

		while (rs.next()) {
			library = new Library();
			library.setBookId(rs.getInt("bookId"));
			library.setBookName(rs.getString("bookName"));
			library.setBookMessage(rs.getString("bookMessage"));
			libraries.add(library);
		}
		return libraries;
	}

}
