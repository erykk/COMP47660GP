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



    }
}

