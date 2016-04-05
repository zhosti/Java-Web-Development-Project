package bg.jwd.libraries.entity.book;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOKS")
public class Book {
	
	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="YEAR_OF_PUBLISHING")
	private Date yearOfPublishing;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(Date yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
