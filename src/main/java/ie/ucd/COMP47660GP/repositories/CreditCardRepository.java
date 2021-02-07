package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Repository;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
    List<CreditCard> findAll();
}
