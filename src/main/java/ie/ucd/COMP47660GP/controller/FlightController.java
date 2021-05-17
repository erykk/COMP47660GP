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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @GetMapping("/addFlight")
    public String newFlight(Model model){
        model.addAttribute("flight", new Flight());
        return  "flight/addFlight";
    }

    @PostMapping(value = "/addFlight")
    public void addNewFlight(@ModelAttribute("flight") Flight flight,
                BindingResult bindingResult, Model model) {
        flightRepository.save(flight);
    }

    @GetMapping("/deleteFlight")
    public String deleteFlight(Model model){
        model.addAttribute("flight", new Flight());
        return  "flight/deleteFlight";
    }

    @PostMapping(value = "/deleteFlight")
    public void deleteFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model){
        List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
        flightRepository.delete(flights.get(0));
    }

    @GetMapping("/findFlight")
    public String findFlight(Model model){
        model.addAttribute("flight", new Flight());
        return "flight/findFlight";
    }

    @PostMapping("/findFlight")
    public String findFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model){
        List<Flight> flights = flightRepository.findFlightByFlightNum(flight.getFlightNum());
        model.addAttribute("flight",flights.get(0));
        return "flight/editFlight";
    }

//    @PreAuthorize("#username == authentication.name")
//    @GetMapping("/editFlight")
//    public String editFlight(@PathVariable("flightNum") String flightNum, Model model) {
//        List<Flight> flights = flightRepository.findFlightByFlightNum(flightNum);
//        model.addAttribute("flight", flights.get(0));
//        return "flight/editFlight2";
//    }
//
     @PostMapping(value = "/editFlight")
     public void updateFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        flightRepository.updateFlightInfo(flight.getSource(), flight.getDestination(), flight.getFlightNum());
//        return "flight/editFlight2";
    }
}
