package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.model.FlightDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FlightDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FlightDetails flightDetails = (FlightDetails) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destination", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTime", "NotEmpty");
        if (flightDetails.getDateTime().length() < 2) {
            errors.rejectValue("dateTime", "flightDetails.dateTime", "Enter valid date");
        }
    }
}
