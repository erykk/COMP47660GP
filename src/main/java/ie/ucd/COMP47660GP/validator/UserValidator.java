package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.service.impl.UserService;
import ie.ucd.COMP47660GP.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if ((user.getUsername().length() < 6 || user.getUsername().length() > 32) ||
            (!isUserValid(user.getUsername())) ||
            (userService.findByUsername(user.getUsername()) != null) ||
            (!user.getPasswordConfirm().equals(user.getPassword())) ||
            (!isStrong(user.getPassword()))
        )

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","NotEmpty");
        if (!user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            errors.rejectValue("email", "userCredentials.email", "Invalid email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phoneNum","NotEmpty");
        if (!user.getPhoneNum().matches("[0-9]+") || user.getPhoneNum().length() < 4) {
            errors.rejectValue("phoneNum", "userCredentials.tel", "Invalid phone number");
        }
        if(userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username", "userCredentials.username", "Username already exists");
        }

    }

    private boolean isUserValid(String username){
        return username.matches("^[a-z0-9_-]{6,32}$");
    }

    private static boolean isStrong(String password){
        final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!^&(){}><]).{8,32})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

// (?=.*[a-z])   The string must contain at least 1 lowercase alphabetical character
// (?=.*[A-Z])	The string must contain at least 1 uppercase alphabetical character
// (?=.*[0-9])	The string must contain at least 1 numeric character
// (?=.*[!@#$%^&*])	The string must contain at least one special character, but we are escaping reserved RegEx characters to avoid conflict
// (?=.{8,})	The string must be eight characters or longer
        // ((?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})


    }
}

