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
public class UserEditValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

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
            errors.rejectValue("username", "userCredentials.username", "Username taken");
        }
    }

    public boolean validDelete(String username, String password){
        if (username.length() < 1 || password.length() < 1){
            return false;
        }
        return true;
    }

    private boolean isUserValid(String username){
        return username.matches("^[a-z0-9_-]{6,32}$");
    }

    private static boolean isStrong(String password){
        final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!^&(){}><]).{8,32})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

