package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.CLogger;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
        CLogger.info("Requesting all flight information");
        return "flight/flightslist";
    }

    // GET all flights for given airports and between now and given date.
    @GetMapping(value = "/flight", params = {"source", "destination", "dateTime"})
    @Validated
    public String getFlights(@RequestParam("source") @NotNull @Min(1) String origin,
                             @RequestParam(value = "destination") @NotNull @Min(1) String dest,
                             @RequestParam(value = "dateTime") @NotNull String dateStr,
                             Model model) {

        if (dateStr.length() < 2){
            model.addAttribute("msg", "Enter a valid date");
            Flight flight = new Flight();
            model.addAttribute("flight", flight);
            return "flight/flightslist";
        }

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

        CLogger.info("Retrieving flight: " + origin + " " + dest + " " + dateStr);


        return "flight/flightslist";
    }
}
