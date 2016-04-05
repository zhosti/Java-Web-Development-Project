package bg.jwd.libraries.dao.book;

import java.util.List;

import bg.jwd.libraries.entity.book.Book;

public interface BookDao {
	List<Book> getBooks();
	
	boolean addBook(Book book);

	Book findById(long id);
	
	boolean updateBook(Book book, long id);
	
	boolean deleteBook(long id);
}
