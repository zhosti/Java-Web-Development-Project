package bg.jwd.libraries.dao.authority;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AuthorityDaoImpl implements AuthorityDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public boolean addUserAuthority(Long userId, int authorityId) {
		entityManager.createNativeQuery("INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID)" + "VALUES(?, ?)")
			.setParameter(1, userId)
			.setParameter(2, authorityId)
			.executeUpdate();

		return true;	
	}
}
