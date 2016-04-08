package bg.jwd.libraries.dao.book;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.libraries.dto.lend.LendInfo;
import bg.jwd.libraries.entity.book.Book;

@Repository
public class BookDaoImpl implements BookDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Book> getBooks() {
		String userQuery = "SELECT a FROM Book a"; 
		Query query = entityManager.createQuery(userQuery, Book.class);	
		List<Book> books = query.getResultList();

		return books != null ? books : null;
	}
	@Override
	@Transactional
	public boolean addBook(Book book) {
		entityManager.createNativeQuery("INSERT INTO BOOKS (NAME, AUTHOR) " +
		    "VALUES(?, ?)")
			.setParameter(1, book.getName())
			.setParameter(2, book.getAuthor())
			.executeUpdate();
		
		return true;
	}
	@Override
	@Transactional
	public boolean updateBook(Book book, long id) {
		entityManager.createNativeQuery("UPDATE BOOKS SET NAME=?, AUTHOR=?, YEAR_OF_PUBLISHING=? WHERE ID=?")
		.setParameter(1,book.getName())
		.setParameter(2, book.getAuthor())
		.setParameter(3,book.getYearOfPublishing())
		.setParameter(4, id)
		.executeUpdate();
		return true;
	}
	@Override
	public Book findById(long id) {
		String userQuery = "SELECT b FROM Book b WHERE b.id=:id"; 
		Query query = entityManager.createQuery(userQuery, Book.class);
		query.setParameter("id", id);		

		List<Book> books = query.getResultList();

		return books != null ? books.get(0) : null;
	}
	@Override
	@Transactional
	public boolean deleteBook(long id) {
		entityManager.createNativeQuery("DELETE FROM BOOKS WHERE ID=?")
			.setParameter(1, id)
			.executeUpdate();
		return true;
	}
	
	@Override
	@Transactional
	public boolean lendBook(long userId, long bookId, Date lendDate, Date endDate) {
		entityManager.createNativeQuery("INSERT INTO BOOK_USER ( BOOK_ID, USER_ID, LENDING_DATE, RETURN_DATE)" + "VALUES(?, ?, ?, ?)")
			.setParameter(1, bookId)
			.setParameter(2,userId)
			.setParameter(3, lendDate)
			.setParameter(4, endDate)
			.executeUpdate();

		return true;
	}
	
	@Override
	public List<LendInfo> getAllLendBooks() {
		Query getLendBookQuery = entityManager.createNativeQuery(
				"SELECT bu.ID AS id, u.ID AS userId, b.ID as bookId, u.USERNAME, b.NAME, b.AUTHOR, bu.LENDING_DATE, bu.RETURN_DATE FROM BOOKS b"
						+ " JOIN BOOK_USER bu" + " ON b.ID = bu.BOOK_ID" + " JOIN LIBRARY_USERS u" + " ON bu.USER_ID = u.ID");

		List<String> queryResult = getLendBookQuery.getResultList();
		List<LendInfo> books = new ArrayList<LendInfo>();
		Iterator i = queryResult.iterator();
		
		while(i.hasNext()) {
			Object[] values = (Object[]) i.next();

			Long lendId = ((BigDecimal) values[0]).longValue();
			Long userId = ((BigDecimal) values[1]).longValue();
			Long bookId = ((BigDecimal) values[2]).longValue();
			String username = (String) values[3];
			String bookName = (String) values[4];
			String author = (String) values[5];
			String dateOfLending = timestampToDate((Timestamp) values[6]);
			String dateOfReturn = timestampToDate((Timestamp) values[7]);
			LendInfo book = new LendInfo(lendId, userId, bookId, username, bookName, author,
					dateOfLending, dateOfReturn);

			books.add(book);
		}

		return books;
	}
	
	@Override
	public LendInfo getLendBook(long id) {
		Query getLendBookQuery = entityManager
				.createNativeQuery("SELECT ID, LENDING_DATE, RETURN_DATE FROM BOOK_USER WHERE ID = ?");

		getLendBookQuery.setParameter(1, id);

		List result = getLendBookQuery.getResultList();
		List<LendInfo> lends = new ArrayList<LendInfo>();
		Iterator i = result.iterator();
		
		while(i.hasNext()) {
			Object[] values = (Object[]) i.next();

			Long lendId = ((BigDecimal) values[0]).longValue();
			String lendDate = timestampToDate((Timestamp) values[1]);
			String returnDate = timestampToDate((Timestamp) values[2]);

			LendInfo lend = new LendInfo(lendId, lendDate, returnDate);
			lends.add(lend);
		}

		return lends.size() != 0 ? lends.get(0) : null;
	}
	
	@Override
	@Transactional
	public boolean editLendBook(long lendId, Date returnDate) {
		entityManager.createNativeQuery("UPDATE BOOK_USER SET RETURN_DATE=? WHERE ID=?")
			.setParameter(1, returnDate)
			.setParameter(2, lendId)
			.executeUpdate();

		return true;
	}
	
	private String timestampToDate(Timestamp timestamp) {
		String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);

		return dateFormat;
	}
	
}
