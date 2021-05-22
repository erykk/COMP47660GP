package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.model.Booking;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.List;


@Component
public class BookingValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Booking.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public String validateAll(Object o){
        Booking booking = (Booking) o;
        List<User> users = booking.getUsers();
        CreditCard card = booking.getCreditCard();
        String op = "";

        int count = 1;
        for (User u : users) {
            if (u.getFirstName().length() < 1 || u.getLastName().length() < 1 || u.getEmail().length() < 1 || u.getPhoneNum().length() < 1 || u.getAddress().length() < 1) {
                op += "Invalid user credentials for passenger " + count + ". ";
            }
            count++;
        }

        if (card.getCardNum().length() > 16 || card.getCardNum().length() < 1)
            op += "Invalid card number. ";
        if (card.getSecurityCode().length() != 3)
            op += "Invalid CVV. ";
        if (card.getName().length() < 1)
            op += "Invalid name on card. ";
        if (card.getExpiryDate() != null) {
            LocalDate date = LocalDate.parse(card.getExpiryDate());
            if (date.isBefore(LocalDate.now()))
                op += "Invalid expiry date. ";
            op += "Expiry date cannot be empty ";
        }

        return op.length() > 1 ? op : "ok";
    }
}
