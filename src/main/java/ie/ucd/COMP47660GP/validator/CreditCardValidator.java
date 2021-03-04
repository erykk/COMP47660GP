package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.CreditCard;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CreditCardValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {
        CreditCard card = (CreditCard) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNum", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityCode", "NotEmpty");

        if (card.getCardNum().length() != 16) {
            errors.rejectValue("cardNum", "cardCredentials.cardNum", "Must be valid card number");
        }

        if (card.getSecurityCode().length() != 3) {
            errors.rejectValue("securityCode", "cardCredentials.securityCode", "Must be valid CVV");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }
}
