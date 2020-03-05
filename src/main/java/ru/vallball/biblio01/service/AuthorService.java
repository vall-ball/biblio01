package ru.vallball.biblio01.service;

import java.util.List;

import ru.vallball.biblio01.model.Author;

public interface AuthorService {
	
	void save(Author author);

	List<Author> list();

	void delete(Long id);

	Author findAuthorById(Long id);

}
