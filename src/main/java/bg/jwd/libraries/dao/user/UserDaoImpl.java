package bg.jwd.libraries.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

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

}
