package ie.ucd.COMP47660GP.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.util.Map;
import java.util.TreeMap;


@RestController
public class FlightController{

    static int referenceNumber;    // Temp for testing purposes
    private static Map<Integer, String> flights = new TreeMap<>();    // Temp for testing purposes
    private static String [] emails =  new String[5];               // Temp for testing purposes

    static {   // Temp for testing purposes
        flights.put(1,"Paris");
        flights.put(2,"Madrid");
        flights.put(3,"New York");
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

    // POST Request for Flight, returns the flights found with the given userInfo
    @RequestMapping(value="/flights", method= RequestMethod.POST)
    public ResponseEntity<String> createFlightRequest(@RequestBody String userInfo) throws URISyntaxException {

        // search for flights matching userInfo and then post each flight (currently only one uri being created per POST request)

        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/flights/"+referenceNumber++;  // Create new URI for this newly created flight-request
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        // return value will actually be all the flights found
        return new ResponseEntity<>("Tuesday at 10am, check in 8am", headers, HttpStatus.CREATED);  // return info back to client class
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





}
