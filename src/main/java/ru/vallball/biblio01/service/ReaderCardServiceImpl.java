package ru.vallball.biblio01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.biblio01.controller.ReaderCardController;
import ru.vallball.biblio01.dao.ReaderCardRepository;
import ru.vallball.biblio01.model.ReaderCard;
import ru.vallball.biblio01.model.User;

@Service
@Transactional
public class ReaderCardServiceImpl implements ReaderCardService{

	private static final Logger logger = LoggerFactory.getLogger(ReaderCardServiceImpl.class);
	
	@Autowired
	ReaderCardRepository readerCardRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public void save(ReaderCard readerCard) {
		logger.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		logger.info(readerCard.toString());
		User user = userService.findUserById(readerCard.getUser().getId());
		readerCard.setUser(user);
		logger.info("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
		logger.info(readerCard.toString());
		//logger.info(readerCard.getUser().getId().toString());
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
