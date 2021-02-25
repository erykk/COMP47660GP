package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.exception.NoSuchFlightException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAll();

    @Query("select f from Flight f where f.source = ?1 and f.destination = ?2 and f.dateTime between ?3 and ?4")
    List<Flight> findFlightsByRouteAndDate(String origin, String dest, LocalDateTime startDate, LocalDateTime
            endDate) throws NoSuchFlightException;
}
