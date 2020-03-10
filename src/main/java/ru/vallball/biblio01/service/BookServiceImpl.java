package ru.vallball.biblio01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.biblio01.dao.BookRepository;
import ru.vallball.biblio01.model.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> list() {
		return bookRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book findBookById(Long id) {
		return bookRepository.findById(id).get();
	}

}
