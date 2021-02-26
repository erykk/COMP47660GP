package ie.ucd.COMP47660GP.validator;

import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

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
        if (existingUser != null && existingUser.getExec()){
            errors.rejectValue("email", "userCredentials.email", "Account exists already");
        }

        if(user.getPassword().length() < 6) {
            errors.rejectValue("password", "userCredentials.password", "Password too short" );
        }

        if(!user.getPassword().equals(user.getVerifyPassword())){
            errors.rejectValue("password", "userCredentials.password", "Passwords don't match");
        }

    }
}
