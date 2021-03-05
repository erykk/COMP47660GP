package ie.ucd.COMP47660GP;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.LinkedList;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import ie.ucd.COMP47660GP.entities.Reservation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

        User user = new User("Barry", "Murphy", "bbe@gmail.com", "46 Hillside", "0845637893");
        user.setId(6);
//        String s = restTemplate.getForObject("http://localhost:8080/mem/38",String.class);
//        System.out.println(s);

//       // POST User
//        HttpEntity<User> requestUser = new HttpEntity<>(user);
//        ResponseEntity responseUser = restTemplate.postForObject("http://localhost:8080/createMember",requestUser,ResponseEntity.class);
//        System.out.println("\nPOST USER: "+responseUser+"\n");

        CreditCard creditCard = new CreditCard(1,user,"TEST","Mr Debit",ldt,"TEST");
//
//        HttpEntity<CreditCard> request4 = new HttpEntity<>(creditCard);
//        String s2 = restTemplate.postForObject("http://localhost:8080/creditCard",request4,String.class);
//        System.out.println("\n"+s2+"\n");

//        CreditCard c = restTemplate.getForObject("http://localhost:8080/creditCard/TEST",CreditCard.class);
//        System.out.println(c.getName());

        // PUT
        HttpEntity<CreditCard> request7 = new HttpEntity<>(creditCard);
        restTemplate.put("http://localhost:8080/editCreditCardDetails/TEST/VISA DEBIT/TEST/2021/09/15/00/00",request7);

//        CreditCard c = restTemplate.getForObject("http://localhost:8080/creditCard/TEST",CreditCard.class);
//        System.out.println("\nGET: "+c.getCardNum()+"\n");
//
//
//
//        // DELETE member
//        System.out.println();
//        restTemplate.delete("http://localhost:8080/executive-club-members/767");
//
//        // GET email info by getting all members info
//


//          User response5 = restTemplate.getForObject("http://localhost:8080/member/85",User.class);
//          if(response5 != null){
//              System.out.println("\nGET: "+response5.getAddress()+"\n");
//              response5.setAddress("Ivor's gaff");
//          }
//        User user = new User("Mr test", "test", "test113", "testidge", "test");
//





        // PUT update member info
//        HttpEntity<User> request7 = new HttpEntity<>(response5);
//        restTemplate.put("http://localhost:8080/editMemberPersonalInfo",request7);


        // DELETE member
//        System.out.println();
//        restTemplate.delete("http://localhost:8080/deleteMember/37");
////
//        String email = restTemplate.getForObject("http://localhost:8080/getEmail/bb@gmail.com",String.class);
//        System.out.println("\nGET email: "+email+"\n");



        // POST Reservation
//        Reservation res = new Reservation(flight2,user);
//        HttpEntity<Reservation> request = new HttpEntity<>(res);
//        ResponseEntity r = restTemplate.postForObject("http://localhost:8080/reservation",request,ResponseEntity.class);



//        String response5 = restTemplate.getForObject("http://localhost:8080/flight?",String.class);
//        System.out.println("\nGET: "+response5+"\n");




//        // POST Request for Executive Club Members
//        @RequestMapping(value="/executive-club-members", method= RequestMethod.POST)
//        public ResponseEntity<String> createMember(@RequestBody String userDetails) throws URISyntaxException {
//
//            String path = ServletUriComponentsBuilder.fromCurrentContextPath().
//                    build().toUriString()+ "/executive-club-members/"+767;  // Create new URI for this newly created executive member
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(new URI(path));
//
//            return new ResponseEntity<>("New Executive Club Member Created", headers, HttpStatus.CREATED);  // return info back to client class
//        }
    }
}

