package bg.jwd.libraries.service.user;

import java.text.ParseException;
import java.util.List;

import bg.jwd.libraries.entity.user.LibraryUser;

public interface UserService {
	boolean addUser(LibraryUser user) throws ParseException;
	
	LibraryUser getUserByUsername(String username);
	
	List<LibraryUser> getUsers();
	
	LibraryUser getUserById(long id);
	
	boolean adminEditUserById(Long id, int status);
	
	boolean editMyProfile(long id, LibraryUser user);
}
