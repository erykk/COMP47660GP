package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.model.FlightDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class AdminFlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Flight.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Flight flight = (Flight) o;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightNum", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destination", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");
        if (flight.getDate().length() < 2) {
            errors.rejectValue("date", "flight.date", "Enter valid date");
        }
        if (flight.getTime().length() < 2) {
            errors.rejectValue("time", "flight.time", "Enter valid date");
        }

        String dateAndTime = flight.getDate() + " " + flight.getTime();

        try {
            dateTime = LocalDateTime.parse(dateAndTime, formatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                errors.rejectValue("date", "flight.date", "Cannot create flight in the past");
                errors.rejectValue("time", "flight.time", "Cannot create flight in the past");
            }
        } catch (DateTimeParseException e) {
            errors.rejectValue("time", "flight.time", "Enter valid date and time");
            errors.rejectValue("date", "flight.date", "Enter valid date and time");
        }


    }
}
