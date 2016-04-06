package bg.jwd.libraries.controller.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
import bg.jwd.libraries.service.book.BookService;
import bg.jwd.libraries.service.user.UserService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		LibraryUser user = userService.getUserByUsername(name);
		
		model.addAttribute("username", name);
		model.addAttribute("books",bookService.getBooks());
		model.addAttribute("id", user.getId());
		
		return "bookRegister";
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String loadAddBook(Locale locale, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		model.addAttribute("username", name);

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
    public String getEdit(@PathVariable long id, 
    	    HttpServletRequest request, Model model) {
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
}
