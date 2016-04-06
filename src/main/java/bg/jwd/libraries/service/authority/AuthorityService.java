package bg.jwd.libraries.service.authority;

import org.springframework.beans.factory.annotation.Autowired;

import bg.jwd.libraries.dao.authority.AuthorityDao;

public interface AuthorityService {
	
	 boolean addUserAuthority(Long userId, int authorityId);	
}
