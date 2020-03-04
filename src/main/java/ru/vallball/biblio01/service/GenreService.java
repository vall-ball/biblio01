package ru.vallball.biblio01.service;

import java.util.List;

import ru.vallball.biblio01.model.Genre;

public interface GenreService {
	
	void save(Genre genre);

	List<Genre> list();

	void delete(Long id);

	Genre findGenreById(Long id);

}
