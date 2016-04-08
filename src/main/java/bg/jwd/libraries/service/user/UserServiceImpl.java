package bg.jwd.libraries.service.user;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.libraries.dao.user.UserDao;
import bg.jwd.libraries.dto.lend.MyLend;
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

	@Override
	public List<LibraryUser> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public LibraryUser getUserById(long id) {
		return userDao.getUserById(id);
	}

	@Override
	public boolean adminEditUserById(long id, int status) {
		userDao.adminEditUserById(id, status);
		return true;
	}

	@Override
	public boolean editMyProfile(long id, LibraryUser user) {
		userDao.editMyProfile(id, user);
		return true;
	}

	@Override
	public boolean delteProfile(long id) {
		userDao.deleteProfile(id);
		return true;
	}

	@Override
	public List<MyLend> getMyLends(long id) {
		return userDao.getMyLends(id);
	}
}
