package bg.jwd.libraries.dao.user;

import java.text.ParseException;

import bg.jwd.libraries.entity.user.LibraryUser;

public interface UserDao {
	LibraryUser getUser(String username);
	
	boolean addUser(LibraryUser user) throws ParseException;
	
	public LibraryUser getUserByUsername(String username);
}
