package ie.ucd.COMP47660GP;

import java.text.NumberFormat;
import java.util.List;
import java.util.LinkedList;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

public class Client {

    public static void main(String[] args) {

        /**
         * Testing data
         */
        RestTemplate restTemplate = new RestTemplate();

        // POST booking

            HttpEntity<String> request = new HttpEntity<>("Noel Gallagher");
            String response = restTemplate.postForObject("http://localhost:8080/bookings",request,String.class);
            System.out.println("\n"+response+"\n");

        // POST profiles
        HttpEntity<String> request2 = new HttpEntity<>("John");
        String response2 = restTemplate.postForObject("http://localhost:8080/profiles",request2,String.class);
        System.out.println("\n"+response2+"\n");

        // GET flight
        String response3 = restTemplate.getForObject("http://localhost:8080/flights/1",String.class);
        System.out.println("\n"+response2+"\n");

        // POST Register an Executive club member
        HttpEntity<String> request4 = new HttpEntity<>("John");
        String response4 = restTemplate.postForObject("http://localhost:8080/executive-club-members",request4,String.class);
        System.out.println("\n"+response4+"\n");

        // GET email info by getting all members info
        String response5 = restTemplate.getForObject("http://localhost:8080/executive-club-members/",String.class);
        System.out.println("\nGET: "+response5+"\n");

        // PUT update member info
        HttpEntity<String> request7 = new HttpEntity<>("updated member info");
        restTemplate.put("http://localhost:8080/executive-club-members/767",request7);

        // DELETE member
        System.out.println();
        restTemplate.delete("http://localhost:8080/executive-club-members/767");


    }
}

