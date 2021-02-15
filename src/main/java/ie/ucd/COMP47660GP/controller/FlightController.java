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


}
