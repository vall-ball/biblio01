package ru.vallball.biblio01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.biblio01.model.ReaderCard;

public interface ReaderCardRepository extends JpaRepository<ReaderCard, Long>{

}
