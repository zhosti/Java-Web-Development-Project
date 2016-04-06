package bg.jwd.libraries.service.user;

import java.text.ParseException;

import bg.jwd.libraries.entity.user.LibraryUser;

public interface UserService {
	boolean addUser(LibraryUser user) throws ParseException;
	
	LibraryUser getUserByUsername(String username);
}
