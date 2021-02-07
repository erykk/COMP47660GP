package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Repository;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{
    List<Flight> findAll();
}
