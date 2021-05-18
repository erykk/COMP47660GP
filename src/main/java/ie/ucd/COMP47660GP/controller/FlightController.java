package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import org.apache.jasper.compiler.JspUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


@Controller
public class FlightController{

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityServiceImpl securityService;


    // GET all flights
    @GetMapping("/flight")
    public String getFlights(Model model) {
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        List<String> origins = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        origins.add("Dublin");
        destinations.add("London");
        destinations.add("Paris");
        destinations.add("Madrid");
        destinations.add("New York");

        model.addAttribute("origins", origins);
        model.addAttribute("destinations", destinations);


        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        securityService.checkLoggedInStatus(model);
        return "flight/flightslist";
    }

    // GET all flights for given airports and between now and given date.
    @GetMapping(value = "/flight", params = {"source", "destination", "dateTime"})
    public String getFlights(@RequestParam("source") String origin,
                             @RequestParam(value = "destination") String dest,
                             @RequestParam(value = "dateTime") String dateStr,
                             Model model) {
        LocalDateTime now = LocalDateTime.now();
        // Add one day to include all flights of selected date
        LocalDate date = LocalDate.parse(dateStr).plusDays(1);
        // TODO: Consider order descending by date
        List<Flight> flights = flightRepository.findFlightsByRouteAndDate(origin, dest, now, date.atStartOfDay());
        model.addAttribute("flights", flights);
        List<String> origins = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        origins.add("Dublin");
        destinations.add("London");
        destinations.add("Paris");
        destinations.add("Madrid");
        destinations.add("New York");

        model.addAttribute("origins", origins);
        model.addAttribute("destinations", destinations);


        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        securityService.checkLoggedInStatus(model);

        return "flight/flightslist";
    }

    /**
     *
     * Admin Methods
     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addFlight")
    public String newFlight(Model model){
        model.addAttribute("flight", new Flight());
        return  "flight/addFlight";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/addFlight")
    public String addNewFlight(@ModelAttribute("flight") Flight flight,
                BindingResult bindingResult, Model model) {
        LocalDate date = LocalDate.parse(flight.getDate());
        LocalTime time = LocalTime.parse(flight.getTime());
        LocalDateTime localDateTime = LocalDateTime.of(date,time);
        flight.setDateTime(localDateTime);
        flightRepository.save(flight);
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteFlight")
    public String deleteFlight(Model model){
        model.addAttribute("flight", new Flight());
        return  "flight/deleteFlight";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/deleteFlight")
    public String deleteFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model){
        List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
        flightRepository.delete(flights.get(0));
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/findFlight")
    public String findFlight(Model model){
        model.addAttribute("flight", new Flight());
        return "flight/findFlight";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/findFlight")
    @ResponseStatus(HttpStatus.OK)
    public String findFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model){
        List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
        flights.get(0).setDateTime(flights.get(0).getDateTime().minusHours(1));
        System.out.println(flights.get(0).getTime());
        System.out.println(flights.get(0).getDateTime());
        if(flights.size() > 0){
//            System.out.println(java.sql.Timestamp.valueOf(flights.get(0).getDateTime()));
//            if(flights.get(0).getDateTime() != null && flights.get(0).getDate() == null && flights.get(0).getTime() == null){
//                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//                LocalDateTime ldt = flights.get(0).getDateTime();
//                ldt = ldt.minusHours(1);
//                String formattedDateTime = ldt.format(formatter);
//                flights.get(0).setDate(formattedDateTime.substring(0,10));
//                flights.get(0).setTime(formattedDateTime.substring(11));
            model.addAttribute("flight",flights.get(0));
            return "flight/editFlight";
            }
        return "flight/flightNotFound";
        }


//     @PreAuthorize("hasAuthority('ADMIN')")
     @PostMapping(value = "/editFlight")
     @ResponseStatus(HttpStatus.OK)
     public String updateFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        LocalDate date;
        LocalTime time;
        LocalDateTime localDateTime = LocalDateTime.now();
        if(flight.getDate() != null && flight.getTime() != null){
             date = LocalDate.parse(flight.getDate());
             time = LocalTime.parse(flight.getTime());
             localDateTime = LocalDateTime.of(date,time);
            localDateTime = localDateTime.plusHours(1);
//             List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
//             if(flights.size() > 0){
//                 if(flights.get(0).getTime() != null && (!flights.get(0).getTime().equals(flight.getTime()))){
//                     localDateTime = localDateTime.plusHours(1);
//                 }
//             }

         }
         flightRepository.updateFlightInfo(flight.getSource(), flight.getDestination(), localDateTime,
                 flight.getDate(), flight.getTime(),flight.getFlightNum());
         return "admin";
    }


}
