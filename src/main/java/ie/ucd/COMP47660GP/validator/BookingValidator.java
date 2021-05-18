package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.model.Booking;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class BookingValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Booking.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Booking booking = (Booking) o;

        for (User u : booking.getUsers()){
            if (u.getFirstName() == null){
                errors.rejectValue("firstName", "booking.users.firstName","NotEmpty");
            }
        }
    }
}
