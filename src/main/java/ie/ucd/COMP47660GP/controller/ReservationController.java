package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.exception.NoSuchBookingException;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserService userService;

    int ref;   // Testing purposes

    // POST a reservation for the given flight and user id
    // TODO: id params when creating URI
    @PostMapping(value="/bookFlight", params = {"name", "surname", "address", "phone", "email"})
    public ResponseEntity<String> createReservation(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname,
                                                    @RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone,
                                                    @RequestParam(value = "email") String email, @RequestBody String flight) throws URISyntaxException {

        //reservationRepository.createReservation(name, surname, address, phone, email);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/user/{user_id}/reservations/{reservation_id}"+ref++;  // Create new URI for reservation
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));

        return new ResponseEntity<>("TEST", headers, HttpStatus.CREATED);  // return info back to client class
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

