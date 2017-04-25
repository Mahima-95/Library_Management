package com.library.request;

public class LibraryRequest {
	private String bookName;
	private int bookId;
	private String bookMessage;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookMessage() {
		return bookMessage;
	}

	public void setBookMessage(String bookMessage) {
		this.bookMessage = bookMessage;
	}

	@Override
	public String toString() {
		return "Library [bookName=" + bookName + ", bookId=" + bookId + ", bookMessage=" + bookMessage + "]";
	}

}
