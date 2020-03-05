package ru.vallball.biblio01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.biblio01.dao.AuthorRepository;
import ru.vallball.biblio01.model.Author;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public void save(Author author) {
		authorRepository.save(author);
	}

	@Override
	public List<Author> list() {
		return authorRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		authorRepository.deleteById(id);
	}

	@Override
	public Author findAuthorById(Long id) {
		return authorRepository.findById(id).get();
	}
	
}
