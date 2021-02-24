package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchBookingException;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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

import javax.validation.Valid;

@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    int ref;   // Testing purposes

    // POST a reservation for the given flight and user id
//    // TODO: id params when creating URI
//    @PostMapping(value="/bookFlight", params = {"name", "surname", "address", "phone", "email"})
//    public ResponseEntity<String> createReservation(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname,
//                                                    @RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone,
//                                                    @RequestParam(value = "email") String email, @RequestBody String flight) throws URISyntaxException {
//
//        return reservationRepository.save();
//
//        //reservationRepository.createReservation(name, surname, address, phone, email);
//        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
//                build().toUriString()+ "/reservations/"+ref++;  // Create new URI for reservation
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(new URI(path));
//
//        return new ResponseEntity<>("Reservation Booked Successfully", headers, HttpStatus.CREATED);  // return info back to client class
//    }

    // TODO: id params when creating URI
    // POST Request for Profile

    @PostMapping("/bookFlight")
    public Reservation createReservation(@Valid @RequestBody() Reservation reservation) throws URISyntaxException {

        return reservationRepository.save(reservation);

//        System.out.println(reservation.getFlight().getFlightNum());
//        //reservationRepository.createReservation(name, surname, address, phone, email);
//        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
//                build().toUriString()+ "/reservations/"+ref++;  // Create new URI for reservation
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(new URI(path));
//
//        return new ResponseEntity<>("Reservation Booked Successfully", headers, HttpStatus.CREATED);  // return info back to client class
    }


    // GET all reservations associated with given user id
    @GetMapping(value = "/reservation", params = {"user_id"})
    public List<Reservation> getReservations(@RequestParam(value = "user_id") int user_id) {

        return reservationRepository.findReservations(user_id);
    }

    @PatchMapping("/reservation/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String cancelReservation(@PathVariable("id") int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NoSuchBookingException(id));
        reservation.setCancelled(true);
        reservationRepository.save(reservation);

        // Update to page of where request came from
        return "redirect:/somehwere";
    }

}

