package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchCreditCardException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
    List<CreditCard> findAll();

    @Query("Select c from CreditCard c where c.cardNum  = :cardNum")
    CreditCard findByCardNum(String cardNum) throws NoSuchCreditCardException;

    @Query("select c from CreditCard c where c.user = :user")
    List<CreditCard> findAllByUser(User user);

    @Transactional
    @Modifying
    @Query("update CreditCard c set c.name = :card_name, c.securityCode = :cvv, c.expiryDate = :date where c.cardNum = :card_num")
    void updateCreditCardInfo(String card_num, String card_name, String cvv, String date) throws NoSuchCreditCardException;
}
