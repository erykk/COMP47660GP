package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository cardRepository;

    public void save(CreditCard card) {
        card.setName(card.getName());
        card.setCardNum(card.getCardNum());
        card.setExpiryDate(card.getExpiryDate());
        card.setSecurityCode(card.getSecurityCode());
        cardRepository.save(card);
    }

    public CreditCard findByCardNum(String cardNum) {
        return cardRepository.findByCardNum(cardNum);
    }

    public CreditCard findByCardNumAndUser(String cardNum, User user) { return cardRepository.findByCardNumAndUser(cardNum, user); }

    public List<CreditCard> findAllByUser(User user) { return cardRepository.findAllByUser(user); }

}
