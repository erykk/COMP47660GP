package ie.ucd.COMP47660GP;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.LinkedList;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import ie.ucd.COMP47660GP.entities.Reservation;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Client {

    public static <DateTime> void main(String[] args) {

        /**
         * Testing data
         */
        RestTemplate restTemplate = new RestTemplate();

        LocalDate localDate = LocalDate.of(2021,05,18);
        LocalTime localTime = LocalTime.of(10,15);
        LocalDateTime ldt = LocalDateTime.of(localDate,localTime);
        Flight flight = new Flight("Dublin","Paris",ldt,"GE5678");
        flight.setId(111);
        User user = new User("Barry", "Murphy","bb@gmail.com","46 Hillside", "0845637893");
        System.out.println(flight.getDestination() + "  : " + flight.getId());
        Reservation reservation = new Reservation(12,flight, user);
        Flight flight2 = new Flight("Dublin","Zurich",ldt,"GE5678");
        System.out.println(flight2.getDestination() + "  : " + flight2.getId());
        // POST Flight
        HttpEntity<Flight> requestFlight = new HttpEntity<>(flight);
        ResponseEntity responseFlight = restTemplate.postForEntity("http://localhost:8080/flight",requestFlight,String.class);
        System.out.println("\n"+responseFlight+"\n");
        // POST User
        HttpEntity<User> requestUser = new HttpEntity<>(user);
        ResponseEntity responseUser = restTemplate.postForEntity("http://localhost:8080/executive-club-member",requestUser,String.class);
        System.out.println("\nPOST USER: "+responseUser+"\n");

        // POST Reservation
//        HttpEntity<Reservation> request = new HttpEntity<>(reservation);
//        Reservation response = restTemplate.postForObject("http://localhost:8080/bookFlight",request,Reservation.class);
//        System.out.println("\n"+response+"\n");
//
//        // POST Register an Executive club member
//        HttpEntity<String> request4 = new HttpEntity<>("John");
//        String response4 = restTemplate.postForObject("http://localhost:8080/executive-club-members",request4,String.class);
//        System.out.println("\n"+response4+"\n");
//
//        // PUT update member info
//        HttpEntity<String> request7 = new HttpEntity<>("updated member info");
//        restTemplate.put("http://localhost:8080/executive-club-members/767",request7);
//
//        // DELETE member
//        System.out.println();
//        restTemplate.delete("http://localhost:8080/executive-club-members/767");
//
//        // GET email info by getting all members info
//        String response5 = restTemplate.getForObject("http://localhost:8080/executive-club-members/",String.class);
//        System.out.println("\nGET: "+response5+"\n");


    }
}

