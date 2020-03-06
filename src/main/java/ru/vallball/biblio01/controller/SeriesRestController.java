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

import ru.vallball.biblio01.model.Series;
import ru.vallball.biblio01.service.SeriesService;

@RestController
@RequestMapping(value = "/series", produces = "application/json")
public class SeriesRestController {
	
	@Autowired
	SeriesService seriesService;
	
	@GetMapping
	@ResponseBody
	public List<Series> list() {
		return seriesService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Series get(@PathVariable(value = "id") Long id) {
		return seriesService.findSeriesById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Series series) {
		try {
			seriesService.save(series);
			return new ResponseEntity<>("Series is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Такая серия существует", e);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Series series) {
		try {
			Series seriesForUpdate = seriesService.findSeriesById(id);
			seriesForUpdate.setName(series.getName());
			seriesForUpdate.setAuthors(series.getAuthors());
			seriesForUpdate.setGenres(series.getGenres());
			seriesService.save(seriesForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Series not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Series is updated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			seriesService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Series not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Series is deleted successfully", HttpStatus.ACCEPTED);
	}


}
