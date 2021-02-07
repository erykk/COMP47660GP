package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
    List<CreditCard> findAll();
}
