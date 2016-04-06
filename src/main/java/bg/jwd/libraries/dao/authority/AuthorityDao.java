package bg.jwd.libraries.dao.authority;

public interface AuthorityDao {
	boolean addUserAuthority(Long userId, int authorityId);
}
