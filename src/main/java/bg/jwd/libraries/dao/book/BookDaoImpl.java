package bg.jwd.libraries.dao.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
}
