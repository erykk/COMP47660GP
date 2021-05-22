package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.CLogger;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchBookingException;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AdminController {
    
    @Autowired
    SecurityServiceImpl securityService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;

    /**************************************
     *
     *           ADMIN User
     **************************************/

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAdminPage(Model model){
        securityService.checkLoggedInStatus(model);
        CLogger.info("/admin", "access" , SecurityContextHolder.getContext());
        return  "admin";
    }

    /****************************
     *
     *        ADMIN Reservation
     ****************************/

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/reservation")
    public String findReservation(@RequestParam(value = "reservation_id", required = false) Integer reservation_id,
                                  Model model) {
        securityService.checkLoggedInStatus(model);
        Reservation reservation;
        CLogger.info("/admin/reservation", "find reservation for id: " + reservation_id , SecurityContextHolder.getContext());
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
                CLogger.warn("/admin/reservation", "no reservation found for id: " + reservation_id , SecurityContextHolder.getContext());
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
        CLogger.info("/admin/editReservation", "get reservation for id: " + id, SecurityContextHolder.getContext());
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
        CLogger.info("/admin/editReservation", "update reservation for id: " + reservation.getId(), SecurityContextHolder.getContext());
        User user = userRepository.findUserByID(reservation.getUserID());
        reservationRepository.updateReservationInfo(reservation.getCancelled(),reservation.getFlight(), user, reservation.getId());
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/deleteReservation")
    public String removeReservation(@RequestParam(value = "reservation_id", required = false) Integer reservation_id,
                                    Model model) {
        CLogger.info("/admin/deleteReservation", "for id: " + reservation_id, SecurityContextHolder.getContext());
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
                CLogger.warn("/admin/deleteReservation", "no reservation found for id: " + reservation_id, SecurityContextHolder.getContext());
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
        CLogger.info("/admin/deleteReservation", "view for id: " + id, SecurityContextHolder.getContext());
        List<Reservation> reservations = reservationRepository.findReservationWithReservationID(id);
        if(reservations.size() > 0){
            Reservation reservation = reservations.get(0);
            reservationRepository.delete(reservation);
        }
        return "admin";
    }

    /****************************
     *
     *        ADMIN Flight
     ****************************/

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addFlight")
    public String newFlight(Model model){
        CLogger.info("/admin/addFlight", "access", SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        model.addAttribute("flight", new Flight());
        return  "flight/addFlight";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/addFlight")
    public String addNewFlight(@ModelAttribute("flight") Flight flight,
                               BindingResult bindingResult, Model model) {
        CLogger.info("/admin/addFlight", "new flight", SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        String dateAndTime = flight.getDate() + " " + flight.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);
        flight.setDateTime(dateTime);
        flightRepository.save(flight);
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteFlight")
    public String deleteFlight(Model model){
        CLogger.info("/admin/deleteFlight", "access", SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        model.addAttribute("flight", new Flight());
        return  "flight/deleteFlight";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/deleteFlight")
    public String deleteFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model){
        CLogger.info("/admin/deleteFlight", "for id: " + flight.getId(), SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
        flightRepository.delete(flights.get(0));
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/findFlight")
    public String findFlight(Model model){
        CLogger.info("/admin/findFlight", "access", SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        model.addAttribute("flight", new Flight());
        return "flight/findFlight";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/findFlight")
    @ResponseStatus(HttpStatus.OK)
    public String findFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model){
        CLogger.info("/admin/findFlight", "for id: " + flight.getId(), SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
        if(flights.size() > 0){
            model.addAttribute("flight",flights.get(0));
            return "flight/editFlight";
        }
        return "flight/flightNotFound";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/editFlight")
    @ResponseStatus(HttpStatus.OK)
    public String updateFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        CLogger.info("/admin/editFlight", "for id: " + flight.getId(), SecurityContextHolder.getContext());
        securityService.checkLoggedInStatus(model);
        LocalDateTime dateTime;
        if(flight.getDate() != null && flight.getTime() != null){
            String dateAndTime = flight.getDate() + " " + flight.getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(dateAndTime, formatter);
            dateTime = dateTime.plusHours(1);
        }
        else{
            dateTime = flight.getDateTime();
        }
        flightRepository.updateFlightInfo(flight.getSource(), flight.getDestination(), dateTime,
                flight.getDate(), flight.getTime(),flight.getFlightNum());
        return "admin";
    }
}
