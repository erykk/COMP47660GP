package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.service.impl.CreditCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CreditCardValidator implements Validator {

    @Autowired
    private CreditCardService creditCardService;

    @Override
    public boolean supports(Class<?> aClass) {
        return CreditCard.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreditCard card = (CreditCard) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNum", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityCode", "NotEmpty");

        CreditCard existingCard = creditCardService.findByCardNum(card.getCardNum());

        if (existingCard != null) {
            errors.rejectValue("cardNum", "cardCredentials.cardNum", "Card is already linked to account");
        }
        if (card.getCardNum().length() != 16) {
            errors.rejectValue("cardNum", "cardCredentials.cardNum", "Must be valid card number");
        }

        if (card.getSecurityCode().length() != 3) {
            errors.rejectValue("securityCode", "cardCredentials.securityCode", "Must be valid CVV");
        }
    }
}
