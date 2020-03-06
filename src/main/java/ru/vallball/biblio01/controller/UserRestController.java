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
import org.springframework.security.crypto.password.PasswordEncoder;
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

import ru.vallball.biblio01.model.User;
import ru.vallball.biblio01.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@GetMapping
	@ResponseBody
	public List<User> list() {
		logger.info("Вы вошли на сайт");
		return userService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public User get(@PathVariable(value = "id") Long id) {
		return userService.findUserById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody User user) {
		try {
			userService.save(user.toUser(passwordEncoder, user.getFirstName(), user.getLastName()));

			return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь с такими именем и фамилией существует", e);

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
		try {
			User userForUpdate = userService.findUserById(id);
			userForUpdate.setDateOfBirth(user.getDateOfBirth());
			userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
			userForUpdate.setFirstName(user.getFirstName());
			userForUpdate.setLastName(user.getLastName());
			userForUpdate.setRole(user.getRole());
			userForUpdate.setLogin(user.getLogin());
			userService.save(userForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("User is udated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			userService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("User is deleted successfully", HttpStatus.ACCEPTED);
	}

}
