package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
public class FlightController{

    static int referenceNumber;    // Temp for testing purposes
    private static Map<Integer, String> flights = new TreeMap<>();    // Temp for testing purposes
    private static String [] emails =  new String[5];               // Temp for testing purposes

//    @Autowired
//    CreditCardRepository creditCardRepository;
//
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    static {   // Temp for testing purposes
        flights.put(1,"Paris");
        flights.put(2,"Madrid");
        flights.put(3,"New York");
    }

    // GET all flights
    @GetMapping("/flight")
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    // GET all flights for given airports and between now and given date.
    @GetMapping(value = "/flight", params = {"origin", "dest", "date"})
    public List<Flight> getFlights(@RequestParam(value = "origin") String origin,
                             @RequestParam(value = "dest") String dest,
                             @RequestParam(value = "date") String dateStr) {
        LocalDateTime now = LocalDateTime.now();
        // Add one day to include all flights of selected date
        LocalDate date = LocalDate.parse(dateStr).plusDays(1);
        // TODO: Consider order descending by date
        return flightRepository.findFlightsByRouteAndDate(origin, dest, now, date.atStartOfDay());
    }

    // Add flight to DB
    // TODO: Considering removing, or at least securing to admin only
    @PostMapping("/flight")
    public Flight addFlight(@RequestBody() Flight flight) {
        return flightRepository.save(flight);
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public User getUser(@PathVariable String id) {
//
        return userRepository.findUser(id);
    }

    @RequestMapping(value="/reservation", method= RequestMethod.POST)
    public ResponseEntity<String> createFlight(@RequestBody Flight flight) throws URISyntaxException {

        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/flights/"+referenceNumber++;  // Create new URI for this newly created flight-request
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        // return value will actually be all the flights found
        return new ResponseEntity<>("Tuesday at 10am, check in 8am", headers, HttpStatus.CREATED);  // return info back to client class
    }

    // GET Request for Flight, returns the flight (not booking) with the given reference
    @RequestMapping(value="/flights/{ref}",method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public String getFlight(@PathVariable int ref) {
//        if (flights.get(location) == null) throw new NoSuchFlightException();  // If no flight exists matching this reference then throw an exception
        return flights.get(1);
    }

    // GET Request for Booking, returns the booking with the given reference i.e. users retrieve their past reservations
    @RequestMapping(value="/bookings/{ref}",method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public String getBooking(@PathVariable int ref) {
//        if (flights.get(location) == null) throw new NoSuchFlightException();  // If no flight exists matching this reference then throw an exception
        return flights.get(1);
    }



    // POST Request for Booking, returns a booking confirmation based on the given flight/userInfo
    @RequestMapping(value="/bookings", method= RequestMethod.POST)
    public ResponseEntity<String> createBooking(@RequestBody String flight) throws URISyntaxException {
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/bookings/"+referenceNumber++;  // Create new URI for this newly created flight
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        // return value will be a booking confirmation (Booking.java if needed)
        return new ResponseEntity<>("Oasis", headers, HttpStatus.CREATED);  // return info back to client class
    }

    // POST Request for Profile
    @RequestMapping(value="/profiles", method= RequestMethod.POST)
    public ResponseEntity<String> createProfile(@RequestBody String userDetails) throws URISyntaxException {
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/profiles/"+referenceNumber++;  // Create new URI for this newly created profile
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        return new ResponseEntity<>("New Profile Created", headers, HttpStatus.CREATED);  // return info back to client class
    }

    // POST Request for Executive Club Members
    @RequestMapping(value="/executive-club-members", method= RequestMethod.POST)
    public ResponseEntity<String> createMember(@RequestBody String userDetails) throws URISyntaxException {

        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/executive-club-members/"+767;  // Create new URI for this newly created executive member
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));

        return new ResponseEntity<>("New Executive Club Member Created", headers, HttpStatus.CREATED);  // return info back to client class
    }

    // POST Request /guest ???

    // PUT Request used to update personal info, credit card details etc. for members
    @RequestMapping(value="/executive-club-members/{referenceNumber}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateMember(@PathVariable int referenceNumber, @RequestBody String memberInfo) throws URISyntaxException  {

        //  if no member exists with that ref number then throw new NoSuchMemberException();

        // update member info
        ResponseEntity<String> update;
        System.out.println("\nPUT Request\n");
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()+ "/executive-club-members/"+referenceNumber;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Location", path);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    // DELETE Request used to delete executive members registration
    @RequestMapping(value="/executive-club-members/{referenceNumber}", method=RequestMethod.DELETE)
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable int referenceNumber) {
        //if no such member exists then throw new NoSuchMemberException();
        // Find member and then delete
        System.out.println("\nDELETE Test\n");
    }


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }






}
