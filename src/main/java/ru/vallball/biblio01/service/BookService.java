package ru.vallball.biblio01.service;

import java.util.List;

import ru.vallball.biblio01.model.Book;

public interface BookService {
	
	void save(Book book);

	List<Book> list();

	void delete(Long id);

	Book findBookById(Long id);

}
