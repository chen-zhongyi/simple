package com.chen.service.imp.person;

import com.chen.dao.person.CardRepository;
import com.chen.enums.CardType;
import com.chen.model.person.Card;
import com.chen.service.imp.BaseServiceImp;
import com.chen.service.person.CardService;
import com.chen.service.person.PersonService;
import com.chen.vos.CardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CardServiceImp extends BaseServiceImp<Card, CardVO> implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PersonService personService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private void setBaseRepository() {
        setBaseRepository(cardRepository);
    }

    @Override
    public Card voToModel(CardVO vo) {
        Card card = new Card();
        card.setPerson(personService.info(vo.getPersonId()));
        return voToModel(card, vo);
    }

    @Override
    public Card voToModel(Card card, CardVO vo) {
        card.setNumber(vo.getNumber() != null ? vo.getNumber() : card.getNumber());
        card.setType(vo.getType() != null ? CardType.convert(vo.getType()) : card.getType());
        return card;
    }

}
