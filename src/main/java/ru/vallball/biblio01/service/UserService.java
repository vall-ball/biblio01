package ru.vallball.biblio01.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.vallball.biblio01.model.User;

public interface UserService extends UserDetailsService {
	
	void save(User user);

	List<User> list();

	void delete(Long id);

	User findUserById(Long id);

}
