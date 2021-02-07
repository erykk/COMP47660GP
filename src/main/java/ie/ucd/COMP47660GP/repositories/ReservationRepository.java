package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    List<Reservation> findAll();
}
