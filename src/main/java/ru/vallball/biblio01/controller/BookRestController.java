package ru.vallball.biblio01.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ru.vallball.biblio01.model.Book;
import ru.vallball.biblio01.service.BookService;

@RestController
@RequestMapping(value = "/books", produces = "application/json")
public class BookRestController {

	private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);

	
	@Autowired
	BookService bookService;

	@GetMapping
	@ResponseBody
	public List<Book> list() {
		return bookService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Book get(@PathVariable(value = "id") Long id) {
		return bookService.findBookById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Book book) {
		logger.info("@PostMapping " + book);
		System.out.println("@PostMapping " + book);
		try {
			bookService.save(book);
			return new ResponseEntity<>("Book is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Такая книга существует", e);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Book book) {
		try {
			Book bookForChange = bookService.findBookById(id);
			bookForChange.setAuthors(book.getAuthors());
			bookForChange.setGenres(book.getGenres());
			bookForChange.setQuantity(book.getQuantity());
			bookForChange.setSeries(book.getSeries());
			bookForChange.setTitle(book.getTitle());
			bookService.save(bookForChange);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Book not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Book is updated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			bookService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Book not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Book is deleted successfully", HttpStatus.ACCEPTED);
	}

}
