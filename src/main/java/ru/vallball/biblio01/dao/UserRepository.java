package ru.vallball.biblio01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.biblio01.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findUserByLogin(String login);
	
}
