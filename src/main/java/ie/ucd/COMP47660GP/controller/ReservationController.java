package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchBookingException;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.stereotype.Controller;
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

@Controller
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/createReservation")
    public ResponseEntity addReservation(@Valid @RequestBody Reservation reservation) throws URISyntaxException  {
        reservationRepository.save(reservation);
        return new ResponseEntity(HttpStatus.CREATED);
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

