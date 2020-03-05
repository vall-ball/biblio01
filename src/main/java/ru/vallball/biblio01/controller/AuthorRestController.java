package ru.vallball.biblio01.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.vallball.biblio01.model.Author;
import ru.vallball.biblio01.service.AuthorService;

@RestController
@RequestMapping(value = "/authors", produces = "application/json")
public class AuthorRestController {
	
	@Autowired
	AuthorService authorService;
	
	@GetMapping
	@ResponseBody
	public List<Author> list() {
		return authorService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Author get(@PathVariable(value = "id") Long id) {
		return authorService.findAuthorById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Author author) {
		try {
			authorService.save(author);
			return new ResponseEntity<>("Author is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Такой автор существует", e);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody Author author) {
		try {
			Author authorForChange = authorService.findAuthorById(id);
			authorForChange.setFirstName(author.getFirstName());
			authorForChange.setLastName(author.getLastName());
			authorForChange.setLanguage(author.getLanguage());
			authorService.save(authorForChange);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Author not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Author is udated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			authorService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Author not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Author is deleted successfully", HttpStatus.ACCEPTED);
	}


}
