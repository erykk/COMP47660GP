package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.exception.NoSuchCreditCardException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
    List<CreditCard> findAll();

    @Query("Select c from CreditCard c where c.cardNum  = :cardNum")
    CreditCard findByCardNum(String cardNum) throws NoSuchCreditCardException;
}
