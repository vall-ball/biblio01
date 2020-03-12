package ru.vallball.biblio01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.biblio01.dao.ReaderCardRepository;
import ru.vallball.biblio01.model.ReaderCard;

@Service
@Transactional
public class ReaderCardServiceImpl implements ReaderCardService{

	@Autowired
	ReaderCardRepository readerCardRepository;
	
	@Override
	public void save(ReaderCard readerCard) {
		readerCardRepository.save(readerCard);
	}

	@Override
	public List<ReaderCard> list() {
		return readerCardRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		readerCardRepository.deleteById(id);
	}

	@Override
	public ReaderCard findReaderCardById(Long id) {
		return readerCardRepository.findById(id).get();
	}

}
