package bg.jwd.libraries.service.book;

import java.util.List;

import bg.jwd.libraries.entity.book.Book;

public interface BookService {
	List<Book> getBooks();
	
	boolean addBook(Book book);
	
	Book getBookById(long id);
	
	boolean updateBook(Book book, long id);
	
	boolean deleteBook(long id);
}
