package ie.ucd.COMP47660GP;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

        CreditCard creditCard = new CreditCard(1,user,"TEST","Mr Debit",ldt,"TEST");

        // PUT
        HttpEntity<CreditCard> request7 = new HttpEntity<>(creditCard);
        restTemplate.put("http://localhost:8080/editCreditCardDetails",request7);
    }
}

