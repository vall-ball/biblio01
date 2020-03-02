package ru.vallball.biblio01.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

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

import ru.vallball.biblio01.model.User;
import ru.vallball.biblio01.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserRestController {

	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	@ResponseBody
	public List<User> list() {
		return userService.list();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public User get(@PathVariable(value = "id") Long id) {
		return userService.findUserById(id);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody User user) {
		userService.save(user.toUser(passwordEncoder, user.getFirstName(), user.getLastName()));
		return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody User user) {
		try {
			User userForUpdate = userService.findUserById(id);
			userForUpdate.setDateOfBirth(user.getDateOfBirth());
			userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
			userForUpdate.setFirstName(user.getFirstName());
			userForUpdate.setLastName(user.getLastName());
			userForUpdate.setRole(user.getRole());
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
