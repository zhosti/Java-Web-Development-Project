package bg.jwd.libraries.controller.book;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.jwd.libraries.dto.lend.LendInfo;
import bg.jwd.libraries.entity.book.Book;
import bg.jwd.libraries.entity.user.LibraryUser;
import bg.jwd.libraries.service.book.BookService;
import bg.jwd.libraries.service.user.UserService;

@Controller
@Secured("ROLE_ADMIN")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		model.addAttribute("user", user);
		model.addAttribute("books",bookService.getBooks());
		
		return "bookRegister";
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String loadAddBook(Locale locale, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		model.addAttribute("user", user);

		return "addBookForm";
	}
	
	@RequestMapping(value = "/createBook", method = RequestMethod.POST)
	public String registerUser(Model model,HttpServletRequest request, @ModelAttribute("book") Book book) {
		String name = request.getParameter("book-name");
		String author = request.getParameter("author");
		
		book.setName(name);
		book.equals(author);
		
		bookService.addBook(book);
				
		return "bookRegister";
	}
	
	@RequestMapping(value = "/editBook/{id}", method = RequestMethod.GET)
    public String getEdit(@PathVariable long id, HttpServletRequest request, Model model) {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		model.addAttribute("user", user);

		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
    
		return "updateBookForm";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBook(@PathVariable int id, 
	    HttpServletRequest request, Model model) throws ParseException {
	
		Book book = bookService.getBookById(id);
		String name = request.getParameter("book-name");
		String author = request.getParameter("author");
		String year = request.getParameter("year");
		
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(year);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    
		book.setName(name);
		book.setAuthor(author);
		book.setYearOfPublishing(sqlDate);
		
		bookService.updateBook(book, book.getId());
		
		return "redirect:"+ "/books";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String deleteBook(@PathVariable int id, 
	    HttpServletRequest request, Model model) throws ParseException {
	
		Book book = bookService.getBookById(id);
		
		bookService.deleteBook(book.getId());
		
		return "redirect:"+ "/books";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/lendBookPage/{id}", method = RequestMethod.GET)
    public String loadLendBook(@PathVariable long id, HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		model.addAttribute("user", user);

		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
    
		return "lendBookForm";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/lendBook/{id}", method = RequestMethod.POST)
	public String lendBook(HttpServletRequest request, @PathVariable int id, Model model)
			throws ParseException {

		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		
		LibraryUser user = userService.getUserByUsername(name);
		String landDate = request.getParameter("lend-date");
		String endDate = request.getParameter("return-date");
		
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(landDate);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());	
		
		this.bookService.lendBook(user.getId(), id, sqlDate, sqlDate1);

		return "redirect:" + "/books";
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/allLends", method = RequestMethod.GET)
	public String alllendsBookPage(Model model) throws ParseException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		model.addAttribute("user", user);

		List<LendInfo> lendsBook = this.bookService.getAllLendBooks();

		model.addAttribute("lendBooks", lendsBook);

		return "allLendBooks";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editBookLend/{bookId}/{id}", method = RequestMethod.GET)
	public String loadEditBookLend(@PathVariable("bookId") long bookId, @PathVariable("id") long lendId, Model model)
			throws ParseException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("id", user.getId());
		model.addAttribute("user", user);
		
		Book book = this.bookService.getBookById(bookId);
		LendInfo lendBook = this.bookService.getLendBook(lendId);

		if (book != null && lendBook != null) {
			model.addAttribute("book", book);
			model.addAttribute("lendBook", lendBook);

			return "editBookLend";

		} else {
			return "redirect:" + "/books";
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "editLend/{bookId}/{id}", method = RequestMethod.POST)
	public String editBookLend(@PathVariable("id") long lendId, Model model, HttpServletRequest request)
			throws ParseException {

		String dateOfReturn = request.getParameter("return-date");

		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfReturn);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		Boolean isEditedLendBook = this.bookService.editLendBook(lendId, sqlDate);

		if (isEditedLendBook) {
			return "redirect:" + "/allLends";
		} else {
			return "bookRegister";
		}
	}
}
