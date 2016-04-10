package bg.jwd.libraries.dao.user;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.libraries.dao.book.BookDaoImpl;
import bg.jwd.libraries.dto.lend.LendInfo;
import bg.jwd.libraries.dto.lend.MyLend;
import bg.jwd.libraries.entity.user.LibraryUser;

@Repository
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public LibraryUser getUser(String username) {
		
		String userQuery = "SELECT n FROM LibraryUser n WHERE n.username = :username"; 
		Query query = entityManager.createQuery(userQuery, LibraryUser.class);
		query.setParameter("username", username);		
		List<LibraryUser> users = query.getResultList();

		return users != null ? users.get(0) : null;

	}
	@Override
	@Transactional
	public boolean addUser(LibraryUser user) throws ParseException {
		
		entityManager.createNativeQuery("INSERT INTO LIBRARY_USERS (USERNAME, PASSWORD, STATUS, PID, BIRTH_DATE) " +
			    "VALUES(?, ?, ?, ?, ?)")
				.setParameter(1, user.getUsername())
				.setParameter(2, user.getPassword())
				.setParameter(3, user.getStatus())
				.setParameter(4, user.getPid())
				.setParameter(5, user.getBirthDate())
				.executeUpdate();
			
			return true;
	}
	
	@Override
	public LibraryUser getUserByUsername(String username) {
		Query query = entityManager.createNativeQuery("SELECT * FROM LIBRARY_USERS WHERE username = ?", LibraryUser.class);
		query.setParameter(1, username);

		List<LibraryUser> users = query.getResultList();

		return users.size() != 0 ? users.get(0) : null;
	}
	
	@Override
	public List<LibraryUser> getUsers() {
		Query query = entityManager.createNativeQuery("SELECT * FROM LIBRARY_USERS", LibraryUser.class);
		List<LibraryUser> users = query.getResultList();

		return users;
	}
	@Override
	public LibraryUser getUserById(long id) {
		Query query = entityManager.createNativeQuery("SELECT * FROM LIBRARY_USERS WHERE id = ?", LibraryUser.class);
		query.setParameter(1, id);

		List<LibraryUser> users = query.getResultList();

		return users != null ? users.get(0) : null;
	}
	
	@Override
	@Transactional
	public boolean adminEditUserById(long id, int status) {
		entityManager.createNativeQuery("UPDATE LIBRARY_USERS SET STATUS=? WHERE id=?")
			.setParameter(1, status)
			.setParameter(2, id)
			.executeUpdate();

		return true;
	}
	
	@Override
	@Transactional
	public boolean editMyProfile(long id, LibraryUser user) {
		entityManager.createNativeQuery("UPDATE LIBRARY_USERS SET USERNAME=?, PASSWORD=?, BIRTH_DATE=? WHERE id=?")
		.setParameter(1, user.getUsername())
		.setParameter(2, user.getPassword())
		.setParameter(3, user.getBirthDate())
		.setParameter(4, id)
		.executeUpdate();
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean deleteProfile(long id) {
		entityManager.createNativeQuery("DELETE FROM USER_AUTHORITY WHERE USER_ID = ?")
			.setParameter(1, id)
			.executeUpdate();
		
		entityManager.createNativeQuery("DELETE FROM BOOK_USER WHERE USER_ID = ?")
			.setParameter(1, id)
			.executeUpdate();
		
		entityManager.createNativeQuery("DELETE FROM LIBRARY_USERS WHERE ID=?")
			.setParameter(1, id)
			.executeUpdate();
		
		return true;
	}
	@Override
	public List<MyLend> getMyLends(long id) {
		Query getLendBookQuery = entityManager.createNativeQuery(
				"SELECT u.USERNAME, b.NAME, b.AUTHOR, bu.LENDING_DATE, bu.RETURN_DATE FROM BOOKS b"
						+ " JOIN BOOK_USER bu" + " ON b.ID = bu.BOOK_ID" + " JOIN LIBRARY_USERS u" + " ON bu.USER_ID = u.ID WHERE u.ID = ?")
				.setParameter(1, id);

		List<String> queryResult = getLendBookQuery.getResultList();
		List<MyLend> userLends = new ArrayList<MyLend>();
		Iterator i = queryResult.iterator();
		
		while(i.hasNext()) {
			Object[] values = (Object[]) i.next();

			String username = (String) values[0];
			String bookName = (String) values[1];
			String author = (String) values[2];
			String dateOfLending = BookDaoImpl.timestampToDate((Timestamp) values[3]);
			String dateOfReturn = BookDaoImpl.timestampToDate((Timestamp) values[4]);
			MyLend myLend = new MyLend(username, bookName, author,
					dateOfLending, dateOfReturn);

			userLends.add(myLend);
		}

		return userLends;
	}
}
