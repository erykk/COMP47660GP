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


    }
}

