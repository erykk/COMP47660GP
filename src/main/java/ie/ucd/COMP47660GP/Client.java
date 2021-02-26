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

        Flight flight2 = new Flight("Dublin","Eindhoven",ldt,"GE5678");
        System.out.println(flight2.getDestination() + "  : " + flight2.getId());

        // POST User
//        HttpEntity<User> requestUser = new HttpEntity<>(user);
//        ResponseEntity responseUser = restTemplate.postForEntity("http://localhost:8080/createMember",requestUser,String.class);
//        System.out.println("\nPOST USER: "+responseUser+"\n");



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
//        String response5 = restTemplate.getForObject("http://localhost:8080/member?id=32&email=bb@gmail.com",String.class);
//        System.out.println("\nGET: "+response5+"\n");

          User response5 = restTemplate.getForObject("http://localhost:8080/member/32",User.class);
         System.out.println("\nGET: "+response5.getAddress()+"\n");

          Reservation reservation = new Reservation(12,flight, response5);

        // POST Reservation
//        HttpEntity<Reservation> request = new HttpEntity<>(reservation);
//        Reservation response = restTemplate.postForObject("http://localhost:8080/addReservation",request,Reservation.class);
//        System.out.println("\n"+response+"\n");


//        String response5 = restTemplate.getForObject("http://localhost:8080/flight?",String.class);
//        System.out.println("\nGET: "+response5+"\n");
    }
}

