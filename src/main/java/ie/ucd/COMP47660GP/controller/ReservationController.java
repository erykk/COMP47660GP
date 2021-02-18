package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    int ref;   // Testing purposes

    // POST a reservation for the given flight and user id
    // TODO: id params when creating URI
    @PostMapping(value="/bookFlight", params = {"name", "surname", "address", "phone", "email"})
    public ResponseEntity<String> createReservation(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname,
                                                    @RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone,
                                                    @RequestParam(value = "email") String email, @RequestBody String flight) throws URISyntaxException {

        reservationRepository.createReservation(name, surname, address, phone, email);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/user/{user_id}/reservations/{reservation_id}"+ref++;  // Create new URI for reservation
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));

        return new ResponseEntity<>("TEST", headers, HttpStatus.CREATED);  // return info back to client class
    }

}

