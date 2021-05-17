package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.exception.NoSuchFlightException;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAll();

    @Query("select f from Flight f where f.source = ?1 and f.destination = ?2 and f.dateTime between ?3 and ?4")
    List<Flight> findFlightsByRouteAndDate(String origin, String dest, LocalDateTime startDate, LocalDateTime
            endDate) throws NoSuchFlightException;

    @Query("select f from Flight f where f.flightNum = :flightNum")
    List<Flight> findFlightByFlightNum(String flightNum) throws NoSuchFlightException;

    @Transactional
    @Modifying
    @Query("update Flight f set f.source = :source, f.destination = :destination, f.dateTime = :dateTime, " +
            " f.date = :date, f.time = :time where f.flightNum = :flightNum")
    void updateFlightInfo(String source, String destination, LocalDateTime dateTime, String date, String time, String flightNum) throws NoSuchFlightException;
}
