package ru.vallball.biblio01.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class ReaderCard {

	@Id
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id")
	private User user;

	@ElementCollection
	@CollectionTable(name = "card_books", joinColumns = @JoinColumn(name = "card_id"))
	@MapKeyJoinColumn(name = "book_id")
	@Column(name = "date")
	private Map<Book, LocalDate> books = new HashMap<>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Book, LocalDate> getBooks() {
		return books;
	}

	public void setBooks(Map<Book, LocalDate> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}
	
	
}
