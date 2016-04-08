package bg.jwd.libraries.dao.book;

import java.sql.Date;
import java.util.List;

import bg.jwd.libraries.dto.lend.LendInfo;
import bg.jwd.libraries.entity.book.Book;

public interface BookDao {
	List<Book> getBooks();
	
	boolean addBook(Book book);

	Book findById(long id);
	
	boolean updateBook(Book book, long id);
	
	boolean deleteBook(long id);
	
	boolean lendBook(long userId, long bookId, Date lendDate, Date endDate);
	
	List<LendInfo> getAllLendBooks();
	
	LendInfo getLendBook(long id);
	
	boolean editLendBook(long lendId, Date returnDate);
}
