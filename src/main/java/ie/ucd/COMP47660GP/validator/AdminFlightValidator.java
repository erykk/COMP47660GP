package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.model.FlightDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdminFlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Flight.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Flight flightDetails = (Flight) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightNum", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destination", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");
        if (flightDetails.getDate().length() < 2) {
            errors.rejectValue("date", "flight.date", "Enter valid date");
        }
        if (flightDetails.getTime().length() < 2) {
            errors.rejectValue("time", "flight.time", "Enter valid date");
        }
    }
}
