package ru.vallball.biblio01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.biblio01.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
