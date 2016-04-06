package bg.jwd.libraries.service.user;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.libraries.dao.user.UserDao;
import bg.jwd.libraries.entity.user.LibraryUser;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean addUser(LibraryUser user) throws ParseException{
		return userDao.addUser(user);
	}

	@Override
	public LibraryUser getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
}
