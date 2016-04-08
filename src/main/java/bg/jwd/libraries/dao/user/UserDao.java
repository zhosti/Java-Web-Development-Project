package bg.jwd.libraries.dao.user;

import java.text.ParseException;
import java.util.List;

import bg.jwd.libraries.dto.lend.MyLend;
import bg.jwd.libraries.entity.user.LibraryUser;

public interface UserDao {
	LibraryUser getUser(String username);
	
	boolean addUser(LibraryUser user) throws ParseException;
	
	public LibraryUser getUserByUsername(String username);
	
	List<LibraryUser> getUsers();
	
	LibraryUser getUserById(long id);
	
	boolean adminEditUserById(long id, int status);
	
	boolean editMyProfile(long id, LibraryUser user);
	
	boolean deleteProfile(long id);
	
	List<MyLend> getMyLends(long userId);
}
