package ru.vallball.biblio01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.biblio01.model.Series;

public interface SeriesRepository extends JpaRepository<Series, Long>{

}
