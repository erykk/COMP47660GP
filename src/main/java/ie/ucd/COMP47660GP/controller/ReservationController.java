package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchBookingException;
import ie.ucd.COMP47660GP.exception.NoSuchCreditCardException;
import ie.ucd.COMP47660GP.exception.NoSuchFlightException;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import ie.ucd.COMP47660GP.model.Booking;
import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
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

    @Autowired
    CreditCardRepository creditCardRepository;


    @GetMapping(value = "/create-reservation/{id}")
    public String addReservation(@PathVariable("id") int id, Model model) {
        Flight flight = flightRepository.findById(id).
                orElseThrow(() -> new NoSuchFlightException(id));
        model.addAttribute("flight", flight);

        return "reservation/num_passengers";
    }

    @GetMapping(value = "/create-reservation/{id}", params = {"num_passengers"})
    public String addReservation(@PathVariable("id") int id, @RequestParam(value = "num_passengers") int numPassengers,
                                 Model model) {
        Flight flight = flightRepository.findById(id).
                orElseThrow(() -> new NoSuchFlightException(id));

        model.addAttribute("booking", new Booking(numPassengers));
        model.addAttribute("flight", flight);

        return "reservation/create_reservation";
    }

    @PostMapping(value = "/create-reservation", consumes = "application/x-www-form-urlencoded")
    public String addReservation(Booking booking, Model model) {
        List<User> users = booking.getUsers();
        User user = null;

        List<User> savedUsers = new LinkedList<>();

        for (User receivedUser: users) {
            try {
                user = userRepository.findEmail(receivedUser.getEmail());
                if (user == null) {
                    throw new NoSuchUserException();
                }
                user.setAddress(receivedUser.getAddress());
                user.setPhoneNum(receivedUser.getPhoneNum());
            } catch (NoSuchUserException e) {
                user = receivedUser;
            }

            user = userRepository.save(user);
            savedUsers.add(user);
        }


        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").
                parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();

        LocalDate expiry = LocalDate.parse(booking.getDateStr(), formatter);

        CreditCard creditCard = booking.getCreditCard();
        try {
            creditCard = creditCardRepository.findByCardNum(creditCard.getCardNum());
            if (creditCard == null) {
                throw new NoSuchCreditCardException();
            }
        } catch (NoSuchCreditCardException e) {
            creditCard = booking.getCreditCard();
        }

        creditCard.setUser(user);
        creditCard.setExpiryDate(expiry.atStartOfDay());
        creditCardRepository.save(creditCard);

        Flight flight = flightRepository.findById(booking.getFlightID()).
                orElseThrow(() -> new NoSuchFlightException(booking.getFlightID()));

        List<Reservation> reservations = new LinkedList<>();

        for (User receivedUser: savedUsers) {
            Reservation reservation = new Reservation();
            reservation.setUser(receivedUser);
            reservation.setFlight(flight);
            reservation.setCancelled(false);

            reservation = reservationRepository.save(reservation);

            reservations.add(reservation);
        }

        model.addAttribute("reservations", reservations);

        return "reservation/reservation_created";
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

    @GetMapping("/reservation-history")
    public String getReservationHistory(User user, Model model) {
        List<Reservation> reservations = reservationRepository.findUsersReservations(user.getId());
        model.addAttribute("reservations", reservations);
        return "reservation_history";
    }
}
