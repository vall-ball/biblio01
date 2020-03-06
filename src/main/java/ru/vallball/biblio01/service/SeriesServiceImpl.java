package ru.vallball.biblio01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.biblio01.dao.SeriesRepository;
import ru.vallball.biblio01.model.Series;

@Service
@Transactional
public class SeriesServiceImpl implements SeriesService{
	
	@Autowired
	SeriesRepository seriesRepository;

	@Override
	public void save(Series series) {
		seriesRepository.save(series);
	}

	@Override
	public List<Series> list() {
		return seriesRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		seriesRepository.deleteById(id);
	}

	@Override
	public Series findSeriesById(Long id) {
		return seriesRepository.findById(id).get();
	}

}
