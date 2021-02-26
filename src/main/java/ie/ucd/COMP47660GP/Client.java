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





//
//        // POST Register an Executive club member
//        HttpEntity<String> request4 = new HttpEntity<>("John");
//        String response4 = restTemplate.postForObject("http://localhost:8080/executive-club-members",request4,String.class);
//        System.out.println("\n"+response4+"\n");
//
//
//
//        // DELETE member
//        System.out.println();
//        restTemplate.delete("http://localhost:8080/executive-club-members/767");
//
//        // GET email info by getting all members info
//        String response5 = restTemplate.getForObject("http://localhost:8080/member?id=32&email=bb@gmail.com",String.class);
//        System.out.println("\nGET: "+response5+"\n");

          User response5 = restTemplate.getForObject("http://localhost:8080/member/78",User.class);
          if(response5 != null){
              System.out.println("\nGET: "+response5.getAddress()+"\n");
              response5.setAddress("Ivor's gaff");
          }

        // POST User
//        HttpEntity<User> requestUser = new HttpEntity<>(response5);
//        ResponseEntity responseUser = restTemplate.postForObject("http://localhost:8080/createMember",requestUser,ResponseEntity.class);
//        System.out.println("\nPOST USER: "+responseUser+"\n");





        // PUT update member info
        HttpEntity<User> request7 = new HttpEntity<>(response5);
        restTemplate.put("http://localhost:8080/editMemberPersonalInfo",request7);


        // DELETE member
//        System.out.println();
//        restTemplate.delete("http://localhost:8080/deleteMember/37");
////
//        String email = restTemplate.getForObject("http://localhost:8080/getEmail/bb@gmail.com",String.class);
//        System.out.println("\nGET email: "+email+"\n");

        // POST Reservation
        Reservation res = new Reservation(222,flight2,response5);
        HttpEntity<Reservation> request = new HttpEntity<>(res);
        ResponseEntity r = restTemplate.postForObject("http://localhost:8080/createReservation",request,ResponseEntity.class);



//        String response5 = restTemplate.getForObject("http://localhost:8080/flight?",String.class);
//        System.out.println("\nGET: "+response5+"\n");
    }
}

