package com.cherylorcutt.spring5webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/** start with POJO **/
/** Add @Entity annotation **/
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String isbn;

	@OneToOne
	private Publisher publisher;

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
	
	public Book( String title, String isbn) {
		this.setTitle(title);
		this.setIsbn(isbn);
	}
	
	public Book( String title, String isbn, Set<Author> authors) {
		this.setTitle(title);
		this.setIsbn(isbn);
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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title=" + title + '\'' +
				", isbn=" + isbn + '\'' +
				"}";
	}
	
	
}
