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

import ru.vallball.biblio01.model.Genre;
import ru.vallball.biblio01.service.GenreService;

@RestController
@RequestMapping(value = "/genres", produces = "application/json")
public class GenreRestController {

	@Autowired
	GenreService genreService;

	@GetMapping
	@ResponseBody
	public List<Genre> list() {
		return genreService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Genre get(@PathVariable(value = "id") Long id) {
		return genreService.findGenreById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Genre genre) {
		genre.setName(genre.getName().toLowerCase());
		try {
			genreService.save(genre);
			return new ResponseEntity<>("Genre is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Жанр с таким названием существует", e);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody Genre genre) {
		try {
			Genre genreForUpdate = genreService.findGenreById(id);
			genreForUpdate.setName(genre.getName().toLowerCase());
			genreService.save(genreForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Genre not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Genre is udated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			genreService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Genre not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Genre is deleted successfully", HttpStatus.ACCEPTED);
	}

}
