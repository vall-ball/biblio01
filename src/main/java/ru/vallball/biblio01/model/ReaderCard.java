package ru.vallball.biblio01.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "cards")
public class ReaderCard {

	@Id
	private Long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "id")
	private User user;

	public void setId(Long id) {
		this.id = id;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "card_books", joinColumns = @JoinColumn(name = "card_id"))
	@MapKeyJoinColumn(name = "book_id")
	@Column(name = "date")
	@JsonDeserialize(using = MyCustomDeserializer.class)
	@JsonSerialize(keyUsing = MyCustomSerializer.class)
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

	@Override
	public String toString() {
		return "ReaderCard [id=" + id + ", user=" + user + ", books=" + books + "]";
	}

	
}
