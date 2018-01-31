package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

/**
 * create some initial data
 * @author corcutt
 *
 */
/* wires it as a springbean */
@Component 
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}
	
	private void initData() {
		
		//Eric
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "1234");
		Publisher pub = new Publisher("Harper Collins", "90231 Berkshire Dr", "Los Angeles", "CA", "90210");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		/**
		 * Need to save the publisher before setting on the book.
		 * Another way to do this would be to create the publisher, save it, then have it in the ctor for the Book.
		 */
		publisherRepository.save(pub);

		ddd.setPublisher(pub);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		//Rod - credited w/starting Spring framework
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "23444");
		Publisher pub2 = new Publisher("Worx", "1234 5th St.", "NY", "NY", "001234");
		rod.getBooks().add(noEJB);

		publisherRepository.save(pub2);

		noEJB.setPublisher(pub2);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}

}
