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

import ru.vallball.biblio01.model.ReaderCard;
import ru.vallball.biblio01.service.ReaderCardService;

@RestController
@RequestMapping(value = "/cards", produces = "application/json")
public class ReaderCardController {

	@Autowired
	ReaderCardService readerCardService;

	@GetMapping
	@ResponseBody
	public List<ReaderCard> list() {
		return readerCardService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ReaderCard get(@PathVariable(value = "id") Long id) {
		return readerCardService.findReaderCardById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid ReaderCard readerCard) {
		try {
			readerCardService.save(readerCard);
			return new ResponseEntity<>("Card is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ReaderCard is exist", e);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ReaderCard readerCard) {
		try {
			ReaderCard readerCardForUpdate = readerCardService.findReaderCardById(id);
			readerCardForUpdate.setBooks(readerCard.getBooks());
			readerCardForUpdate.setUser(readerCard.getUser());
			readerCardService.save(readerCardForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("ReaderCard not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("ReaderCard is updated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			readerCardService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("ReaderCard not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("ReaderCard is deleted successfully", HttpStatus.ACCEPTED);
	}

}
