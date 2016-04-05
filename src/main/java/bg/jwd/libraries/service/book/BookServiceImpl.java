package bg.jwd.libraries.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.libraries.dao.book.BookDao;
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
}
