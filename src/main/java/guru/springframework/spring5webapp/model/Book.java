package guru.springframework.spring5webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/** start with POJO **/
/** Add @Entity annotation **/
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String isbn;
	private String publisher;
	
	/* add JoinTable so only have one join table, not two
	 * and tell it the column name to use. by default generated a books_id
	 * in AUTHOR_BOOKS and a authors_id in BOOK_AUTHORS
	 *  
	 */
	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
	inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<Author>();
	
	public Book() {}
	
	public Book( String title, String isbn, String publisher) {
		this.setTitle(title);
		this.setIsbn(isbn);
		this.setPublisher(publisher);
	}
	
	public Book( String title, String isbn, String publisher, Set<Author> authors) {
		this.setTitle(title);
		this.setIsbn(isbn);
		this.setPublisher(publisher);
		this.setAuthors(authors);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	

}
