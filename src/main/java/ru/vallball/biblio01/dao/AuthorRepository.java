package ru.vallball.biblio01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.biblio01.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
