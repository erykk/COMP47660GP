package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.CLogger;
import ie.ucd.COMP47660GP.model.FlightDetails;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import ie.ucd.COMP47660GP.validator.FlightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;


@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    SecurityServiceImpl securityService;

    @Autowired
    FlightValidator flightValidator;


    // GET all flights
    @GetMapping("/flight")
    public String getFlights(Model model) {
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        model.addAttribute("flightDetails", new FlightDetails());
        List<String> origins = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        origins.add("Dublin");
        destinations.add("London");
        destinations.add("Paris");
        destinations.add("Madrid");
        destinations.add("New York");

        model.addAttribute("origins", origins);
        model.addAttribute("destinations", destinations);

        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("Sec context "+context.getAuthentication().getName());

        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        securityService.checkLoggedInStatus(model);
        CLogger.info("/flight", "view", SecurityContextHolder.getContext());
        return "flight/flightslist";
    }

    // GET all flights for given airports and between now and given date.
    @GetMapping(value = "/flights")
    public String getFlights(@Valid FlightDetails flightDetails,
                             Model model,
                             BindingResult bindingResult) {

        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        model.addAttribute("flightDetails", flightDetails);

        flightValidator.validate(flightDetails,bindingResult);

        String dateStr = flightDetails.getDateTime();
        String origin = flightDetails.getSource();
        String dest = flightDetails.getDestination();

        LocalDateTime now;
        LocalDate date;

        try {
            now = LocalDateTime.now();
            // Add one day to include all flights of selected date
            date = LocalDate.parse(dateStr).plusDays(1);
        } catch (DateTimeParseException e) {
            CLogger.error("/flights", e.toString(), SecurityContextHolder.getContext());
            model.addAttribute("msg", "Please enter a valid date");
            securityService.checkLoggedInStatus(model);
            return "flight/flightslist";
        }

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

        securityService.checkLoggedInStatus(model);

        CLogger.info("/flights", "Retrieving flight [from: " + origin + ", to: " + dest + ", date: " + dateStr + "]", SecurityContextHolder.getContext());

        return "flight/flightslist";
    }

}
