package ru.vallball.biblio01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.biblio01.dao.GenreRepository;
import ru.vallball.biblio01.model.Genre;

@Service
@Transactional
public class GenreServiceImpl implements GenreService{

	@Autowired
	GenreRepository genreRepository;
		
	@Override
	public void save(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	public List<Genre> list() {
		return genreRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		genreRepository.deleteById(id);
	}

	@Override
	public Genre findGenreById(Long id) {
		return genreRepository.findById(id).get();
	}

}
