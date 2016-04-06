package bg.jwd.libraries.dao.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
