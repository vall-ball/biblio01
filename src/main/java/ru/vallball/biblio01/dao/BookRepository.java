package ru.vallball.biblio01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.biblio01.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
