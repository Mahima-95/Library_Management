package com.library.constants;

public interface QueryConstants {

	String ADDBOOKS = "INSERT INTO library(bookId, bookName, bookMessage) VALUES(0,?,?)";

	String UPDATEBOOKS = "UPDATE library SET bookName =?, bookMessage=? WHERE bookId = ?";

	String DELETEBOOKS = "delete from library where bookId=?";

	String GETBOOKS = "select * from library";
}
