package guru.springframework.spring5webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.spring5webapp.repositories.BookRepository;

/**
 * Create a BookController
 * 
 * @author corcutt
 *
 */
/*
 * @Controller registers the class w/Spring as a SpringBean
 */
@Controller
public class BookController {
	
	private BookRepository bookRepository;
	
	/* Spring will autowire bookRepository here */
	public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@RequestMapping("/books")
	public String getBooks(Model model) {
		/* add books to the model */
		model.addAttribute("books", bookRepository.findAll());
		return "books"; // thymeleaf view name
	}

}
