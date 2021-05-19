package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.*;
import ie.ucd.COMP47660GP.model.Booking;
import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import ie.ucd.COMP47660GP.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    SecurityServiceImpl securityService;


    @GetMapping(value = "/create-reservation/{id}")
    public String addReservation(@PathVariable("id") int id, Model model) {
        System.out.println("1 TESTING GET /create-reservation{id}");
        Flight flight = flightRepository.findById(id).
                orElseThrow(() -> new NoSuchFlightException(id));
        model.addAttribute("flight", flight);
        securityService.checkLoggedInStatus(model);

        return "reservation/num_passengers";
    }

    @GetMapping(value = "/create-reservation/{id}", params = {"num_passengers"})
    public String addReservation(@PathVariable("id") int id, @RequestParam(value = "num_passengers") int numPassengers,
                                 Model model) {
        System.out.println("2 TESTING GET /create-reservation{id} 2");
        Flight flight = flightRepository.findById(id).
                orElseThrow(() -> new NoSuchFlightException(id));

        Booking booking = new Booking(numPassengers);

        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());

        if (user != null) {
            booking.getUsers().remove(0);
            booking.getUsers().add(0, user);

            List<CreditCard> creditcards = creditCardRepository.findAllByUser(user);

            if (creditcards != null && !creditcards.isEmpty()) {
                model.addAttribute("creditcards", creditcards);
            }
        }

        model.addAttribute("booking", booking);
        model.addAttribute("flight", flight);
        securityService.checkLoggedInStatus(model);

        return "reservation/create_reservation";
    }

    @PostMapping(value = "/create-reservation", consumes = "application/x-www-form-urlencoded")
    public String addReservation(Booking booking, Model model) {
        System.out.println("3 TESTING  POST /create-reservation ");
        securityService.checkLoggedInStatus(model);
        List<User> users = booking.getUsers();
        User user = null;
        List<User> savedUsers = new LinkedList<>();

        for (User receivedUser: users) {
            try {
                user = userRepository.findByUsername(receivedUser.getUsername());
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

        if (booking.getSavedCard() == null || booking.getSavedCard().equals("")) {
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

            creditCard.setUser(savedUsers.get(0));
            creditCard.setExpiryDate(expiry.atStartOfDay().toString());
            creditCardRepository.save(creditCard);
        }

        Flight flight = flightRepository.findById(booking.getFlightID()).
                orElseThrow(() -> new NoSuchFlightException(booking.getFlightID()));

        List<Reservation> reservations = new LinkedList<>();

        SecurityContext context = SecurityContextHolder.getContext();
        User user2 = userRepository.findByUsername(context.getAuthentication().getName());
        if(user2 != null){
            savedUsers.clear();
            savedUsers.add(user2);
            System.out.println("/create-res user not null");
        }


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

//    @PreAuthorize("#username == authentication.name or hasAuthority('ADMIN')")
    @GetMapping("/reservation")
    public String displayReservation(@RequestParam(value = "reservation_id", required = false) Integer reservation_id,
                                     Model model) {
        System.out.println("4 TESTING GET /reservation");
        securityService.checkLoggedInStatus(model);
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
    public List<Reservation> getReservations(@PathVariable("id") Long id) {

        System.out.println("5 TESTING GET /reservation{id}");
        return reservationRepository.findUsersReservations(id);
    }

//    @PreAuthorize("#username == authentication.name")
    @RequestMapping(value = "/user/deleteGuestReservation/{resID}", method = RequestMethod.GET)
    public String cancelReservation( @PathVariable("resID") int resID,@ModelAttribute("reservation") Reservation reservation, BindingResult br, Model model) {

        System.out.println("TESTING PATCH 222 /reservation{id}");
        Reservation reservation2 = reservationRepository.findById(resID).orElseThrow(() -> new NoSuchBookingException(resID));
        LocalDateTime flightTime = reservation2.getFlight().getDateTime();
//        securityService.checkLoggedInStatus(model);
        // Can only cancel if flight is more than 24 hours away.
        if (flightTime.isAfter(LocalDateTime.now().plusHours(24))) {
            reservation2.setCancelled(true);
            reservationRepository.save(reservation2);
        }
        return "reservation/user_reservation";
    }

    @PreAuthorize("#username == authentication.name")
    @RequestMapping(value = "/user/deleteReservation/{username}/{resID}", method = RequestMethod.GET)
    public String cancelReservation(@PathVariable("username") String username, @PathVariable("resID") int resID, @ModelAttribute("reservation") Reservation reservation, BindingResult br, Model model) {
        User u = userRepository.findByUsername(username);
        model.addAttribute("user", u);
//        System.out.println("6 TESTING PATCH /reservation{id}");
        Reservation reservation2 = reservationRepository.findById(resID).orElseThrow(() -> new NoSuchBookingException(resID));
        LocalDateTime flightTime = reservation2.getFlight().getDateTime();
        securityService.checkLoggedInStatus(model);
        // Can only cancel if flight is more than 24 hours away.
        if (flightTime.isAfter(LocalDateTime.now().plusHours(24))) {
            reservation2.setCancelled(true);
            reservationRepository.save(reservation2);
        }
        return "user/reservationHistory";
    }

    @PreAuthorize("#username == authentication.name or hasAuthority('ADMIN')")
    @GetMapping("/reservationHistory/{username}")
    public String history(Model model, @PathVariable("username") String username) {
//        securityService.checkLoggedInStatus(model);
//        System.out.println("7 TESTING GET /reservationHistory/{username]/{id}");
//        System.out.println(username);
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
//        if(user.getId() != id){
//            throw new UnauthorisedUserException();
//        }
        List<Reservation> reservations = reservationRepository.findUsersReservations(user.getId());

        model.addAttribute("reservations", reservations);
        return "user/reservationHistory";
    }

//    @GetMapping("/reservation-history")
//    public String getReservationHistory(User user, Model model) {
//        securityService.checkLoggedInStatus(model);
//        List<Reservation> reservations = reservationRepository.findUsersReservations(user.getId());
//        model.addAttribute("reservations", reservations);
//        return "reservation_history";
//    }

    /****************************
     *           START
     *        ADMIN Requests
     ****************************/

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/reservation")
    public String findReservation(@RequestParam(value = "reservation_id", required = false) Integer reservation_id,
                                     Model model) {
        securityService.checkLoggedInStatus(model);
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

        return "reservation/findReservation";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editReservation/{id}")
    public String editReservation(@PathVariable("id") int id, Model model){
        List<Reservation> reservations = reservationRepository.findReservationWithReservationID(id);
        Reservation reservation = reservations.get(0);
        reservation.setUserID(reservation.getUser().getId());
        model.addAttribute("reservation", reservation);
        return "reservation/editReservation";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/editReservation")
    public String editReservation(@ModelAttribute("reservation") Reservation reservation,
                                  BindingResult bindingResult, Model model){
        User user = userRepository.findUserByID(reservation.getUserID());
        reservationRepository.updateReservationInfo(reservation.getCancelled(),reservation.getFlight(), user, reservation.getId());
        return "admin";
    }




    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/deleteReservation")
    public String removeReservation(@RequestParam(value = "reservation_id", required = false) Integer reservation_id,
                                  Model model) {
        securityService.checkLoggedInStatus(model);
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
        return "reservation/deleteReservation";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteReservation/{id}")
    public String removeReservation(@PathVariable("id") int id, Model model){
        List<Reservation> reservations = reservationRepository.findReservationWithReservationID(id);
        if(reservations.size() > 0){
            Reservation reservation = reservations.get(0);
            reservationRepository.delete(reservation);
        }
        return "admin";
    }

    /****************************
     *           END
     *        ADMIN Requests
     ****************************/

}
