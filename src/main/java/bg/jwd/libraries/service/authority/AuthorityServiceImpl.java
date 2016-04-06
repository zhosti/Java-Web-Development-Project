package bg.jwd.libraries.service.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.libraries.dao.authority.AuthorityDao;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public boolean addUserAuthority(Long userId, int authorityId) {
		return authorityDao.addUserAuthority(userId, authorityId);
	}
}
