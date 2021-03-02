package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchBookingException;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightRepository flightRepository;

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

    @PostMapping("/createReservation")
    public ResponseEntity addReservation(@Valid @RequestBody Reservation reservation) throws URISyntaxException  {
        reservationRepository.save(reservation);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/reservation")
    public String displayReservation(@RequestParam(value = "reservation_id", required = false) Integer reservation_id,
                                     Model model) {
        Reservation reservation;
        if (reservation_id != null) {
            try {
                reservation = reservationRepository.findById(reservation_id).
                        orElseThrow(() -> new NoSuchBookingException(reservation_id));
                User user = reservation.getUser();
                Flight flight = reservation.getFlight();

                model.addAttribute("user", user);
                model.addAttribute("flight", flight);

            } catch (NoSuchBookingException e) {
                model.addAttribute("error", e.getMessage());
                reservation = new Reservation();
            }
        } else {
            reservation = new Reservation();
        }
        model.addAttribute("reservation", reservation);

        return "reservation/user_reservation";
    }


    // GET all reservations associated with given user id
    @GetMapping(value = "/reservation/{id}")
    public List<Reservation> getReservations(@PathVariable("id") int id) {

        return reservationRepository.findUsersReservations(id);
    }

    @PatchMapping("/reservation/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancelReservation(@PathVariable("id") int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NoSuchBookingException(id));
        reservation.setCancelled(true);
        reservationRepository.save(reservation);
    }

}

