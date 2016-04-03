package bg.jwd.libraries.dao.user;

import bg.jwd.libraries.entity.user.LibraryUser;

public interface UserDao {
	LibraryUser getUser(String username);
}
