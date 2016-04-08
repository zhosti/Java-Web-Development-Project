package bg.jwd.libraries.service.book;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.libraries.dao.book.BookDao;
import bg.jwd.libraries.dto.lend.LendInfo;
import bg.jwd.libraries.entity.book.Book;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	public boolean addBook(Book book) {
		bookDao.addBook(book);
		return true;
	}

	@Override
	public boolean updateBook(Book book, long id) {
		bookDao.updateBook(book, id);
		
		return true;
	}

	@Override
	public Book getBookById(long id) {
		return bookDao.findById(id);
	}

	@Override
	public boolean deleteBook(long id) {
		bookDao.deleteBook(id);
		return true;
	}

	@Override
	public boolean lendBook(long userId, long bookId, Date lendDate, Date endDate) {
		bookDao.lendBook(userId, bookId, lendDate, endDate);
		return true;
	}

	@Override
	public List<LendInfo> getAllLendBooks() {
		
		return bookDao.getAllLendBooks();
	}

	public LendInfo getLendBook(long id){
		return bookDao.getLendBook(id);
	}
	
	@Override
	public boolean editLendBook(long lendId, Date returnDate) {
		bookDao.editLendBook(lendId, returnDate);
		return true;
	}
}
