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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
