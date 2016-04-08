package bg.jwd.libraries.dto.lend;

import java.io.Serializable;

public class MyLend implements Serializable{

	private static final long serialVersionUID = 2964719630024699253L;

	private String username;

	private String bookName;
	
	private String author;

	private String dateOfLending;

	private String dateOfReturn;
	
	
	public MyLend(String username,String bookName,String author, String dateOfLending, String dateOfReturn) {
		this.username = username;
		this.bookName = bookName;
		this.author = author;
		this.dateOfLending = dateOfLending;
		this.dateOfReturn = dateOfReturn;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDateOfLending() {
		return dateOfLending;
	}


	public void setDateOfLending(String dateOfLending) {
		this.dateOfLending = dateOfLending;
	}


	public String getDateOfReturn() {
		return dateOfReturn;
	}


	public void setDateOfReturn(String dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

}
