package bg.jwd.libraries.controller.user;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.jwd.libraries.entity.book.Book;
import bg.jwd.libraries.entity.user.LibraryUser;
import bg.jwd.libraries.service.authority.AuthorityService;
import bg.jwd.libraries.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String loadAddUser(Locale locale, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		model.addAttribute("username", name);

		return "addUserForm";
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, @ModelAttribute("user") LibraryUser user)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, ParseException {

		String username = request.getParameter("username");
		int role = Integer.parseInt(request.getParameter("role"));
		String birthDate = request.getParameter("dateOfBirth");
		
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		user.setBirthDate(sqlDate);
		
		encryptPasswordMD5(user);

		LibraryUser checkIfUserExist = this.userService.getUserByUsername(username);

		if (checkIfUserExist == null) {
			Boolean isAdded = this.userService.addUser(user);
			LibraryUser dbUser = this.userService.getUserByUsername(username);
			
			Boolean isUserAddedToRol = this.authorityService.addUserAuthority(dbUser.getId(), role);

			if (isAdded == true) {
				return "redirect:" + "/books";

			} else {
				return "addUserForm";
			}
		} else {
			return "addUserForm";
		}
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getUsers());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		
		return "users";
	}
	
	@RequestMapping(value = "/adminEditUser" + "/{id}", method = RequestMethod.GET)
	public String editUserPage(@PathVariable("id") long userId, Model model) {
		
		LibraryUser user = this.userService.getUserById(userId);

		if (user != null) {
			model.addAttribute("user", user);

			return "adminEditPage";

		} else {
			return "redirect:" + "/home";
		}
	}
	
	@RequestMapping(value = "/editUser" + "/{id}", method = RequestMethod.POST)
	public String editUser(@PathVariable("id") long userId, Model model, HttpServletRequest request) {

		int status = Integer.parseInt(request.getParameter("status_select"));
		Boolean isEdited = this.userService.adminEditUserById(userId, status);

		if (isEdited == true) {
			return "redirect:" + "/users";

		} else {
			return "adminEditPage";
		}
	}
	
	@RequestMapping(value = "/editProfile" + "/{id}", method = RequestMethod.GET)
	public String editProfile(@PathVariable("id") long userId, Model model) {
		
		LibraryUser user = userService.getUserById(userId);

		if (user != null) {
			model.addAttribute("user", user);

			return "editUserProfile";

		} else {
			return "redirect:" + "/home";
		}
	}
	
	@RequestMapping(value = "/editUserProfile/{id}", method = RequestMethod.POST)
	public String editUserProfile(@PathVariable int id, 
	    HttpServletRequest request, Model model) throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
		
		
		LibraryUser user = userService.getUserById(id);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String birthDate = request.getParameter("date-birth");
		
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setBirthDate(sqlDate);
	    
	    encryptPasswordMD5(user);
	    userService.editMyProfile(user.getId(), user);
	    
	    return "redirect:" + "/logout";
	}
	
	@RequestMapping(value = "/deleteProfile/{id}", method = RequestMethod.POST)
	public String deleteBook(@PathVariable long id, 
	    HttpServletRequest request, Model model) throws ParseException {
	
		LibraryUser book = userService.getUserById(id);
		
		userService.delteProfile(book.getId());
		
		return "redirect:"+ "/users";
	}
	
	private void encryptPasswordMD5(LibraryUser user) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(user.getPassword().getBytes("UTF-8"));
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(16);

		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}

		user.setPassword(hashtext.toString());
	}
}
