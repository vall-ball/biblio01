package ru.vallball.biblio01.service;

import java.util.List;

import ru.vallball.biblio01.model.ReaderCard;

public interface ReaderCardService {
		
	void save(ReaderCard readerCard);

	List<ReaderCard> list();

	void delete(Long id);

	ReaderCard findReaderCardById(Long id);

}
