package bg.jwd.libraries.dto.lend;

import java.io.Serializable;

public class LendInfo implements Serializable{

	private static final long serialVersionUID = -8084671914853564922L;

	private Long id;

	private Long userId;

	private Long bookId;

	private String username;

	private String bookName;
	
	private String author;

	private String dateOfLending;

	private String dateOfReturn;
	
	public LendInfo(Long id, String dateOfLending, String dateOfReturn) {
		this.id = id;
		this.dateOfLending = dateOfLending;
		this.dateOfReturn = dateOfReturn;
	}

	public LendInfo(Long id, Long userId, Long bookId, String username,
			String bookName,String author, String dateOfLending, String dateOfReturn) {
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.username = username;
		this.bookName = bookName;
		this.author = author;
		this.dateOfLending = dateOfLending;
		this.dateOfReturn = dateOfReturn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}
