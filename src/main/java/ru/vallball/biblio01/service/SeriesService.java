package ru.vallball.biblio01.service;

import java.util.List;

import ru.vallball.biblio01.model.Series;

public interface SeriesService {

	void save(Series series);

	List<Series> list();

	void delete(Long id);

	Series findSeriesById(Long id);
}
