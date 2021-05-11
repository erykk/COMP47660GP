package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class
LoginValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verifyPassword", "NotEmpty");

        User existingUser = userService.findByEmail(user.getEmail());
        // Exec user exists, allow
        if (existingUser != null && existingUser.getExec()) {
            errors.rejectValue("email", "userCredentials.email", "Account exists already");
        }
        if (!isEmailValid(user.getEmail())) {
            errors.rejectValue("email", "userCredentials.email", "Must be valid email");
        }

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "userCredentials.password", "Password too short");
        }

        if (!checkNumber(user.getPassword())) {
            errors.rejectValue("password", "userCredentials.password", "Password needs to contain a number");
        }
        if (!checkUpper(user.getPassword())) {
            errors.rejectValue("password", "userCredentials.password",
                    "Password needs to contain an upper case letter");
        }
        if (!checkLower(user.getPassword())) {
            errors.rejectValue("password", "userCredentials.password", "Password needs to contain a lower case letter");
        }

        if (!user.getPassword().equals(user.getVerifyPassword())) {
            errors.rejectValue("password", "userCredentials.password", "Passwords don't match");
        }

    }

    private boolean checkNumber(String password) {
        boolean numberCheck = false;
        char ch;
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                numberCheck = true;
            }
        }
        return numberCheck;
    }

    private boolean checkUpper(String password) {
        boolean upperCheck = false;
        char ch;
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                upperCheck = true;
            }
        }
        return upperCheck;
    }

    private boolean checkLower(String password) {
        boolean lowerCheck = false;
        char ch;
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                lowerCheck = true;
            }
        }
        return lowerCheck;
    }

    private boolean isEmailValid(String email) {
        boolean validEmail = false;
        Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher match = emailPattern.matcher(email);
        if (match.matches()) {
            validEmail = true;
        }
        return validEmail;
    }

    public void testing(){

    }
}
