package ie.ucd.COMP47660GP.repositories;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.exception.NoSuchFlightException;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAll();

    @Query("select f from Flight f where f.source = :origin and f.destination = :dest and f.dateTime between :startDate and :endDate")
    List<Flight> findFlightsByRouteAndDate(String origin, String dest, LocalDateTime startDate, LocalDateTime
            endDate) throws NoSuchFlightException;
}
