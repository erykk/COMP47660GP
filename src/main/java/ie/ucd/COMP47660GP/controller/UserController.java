package ie.ucd.COMP47660GP.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;

import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.LoginService;
import ie.ucd.COMP47660GP.service.impl.CreditCardService;
import ie.ucd.COMP47660GP.service.impl.LoginServiceImpl;
import ie.ucd.COMP47660GP.service.impl.SecurityService;
import ie.ucd.COMP47660GP.service.impl.UserService;
import ie.ucd.COMP47660GP.validator.CreditCardValidator;
import ie.ucd.COMP47660GP.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Controller
public class UserController {

    @Autowired
    LoginValidator loginValidator;
    @Autowired
    CreditCardValidator cardValidator;
    @Autowired
    UserService userService;
    @Autowired
    CreditCardService cardService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Qualifier("loginServiceImpl")
    LoginService loginService;
    @Autowired
    SecurityService securityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CreditCardRepository creditCardRepository;

    @GetMapping("getEmail/{email}")
    @ResponseBody
    public String checkIfEmailIsValid(@PathVariable String email) {
        User user = userRepository.findEmail(email);
        if (user == null) {
            return "Invalid: Email already exists";
        }
        return "Valid: email does not exist";
    }

    @PutMapping("/editPersonalDetails")
    @ResponseBody
    public void updatePersonaDetails(@RequestBody User updatedUser) {
        userRepository.updateUserId(updatedUser.getAddress(), updatedUser.getFirstName(), updatedUser.getLastName(),
                updatedUser.getPhoneNum(), updatedUser.getEmail());
    }

    @GetMapping("/registerCard")
    public String registerCard(Model model) {
        model.addAttribute("cardCredentials", new CreditCard());
        return "user/cardRegistration";
    }

    @RequestMapping(value = "/creditCard", method = RequestMethod.POST)
    public String addCreditCard(@ModelAttribute("cardCredentials") CreditCard cardCredentials,
            BindingResult bindingResult, Model model) {
        List<CreditCard> creditCards = new LinkedList<>();
        cardValidator.validate(cardCredentials, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/cardRegistration";
        }

        cardService.save(cardCredentials);

        model.addAttribute("cardCredentials", cardCredentials);
        model.addAttribute("msg", "Successfully added card " + cardCredentials.getCardNum() + ".");
        creditCards.add(cardCredentials);
        model.addAttribute("creditCards", creditCards);
        return "user/viewCards";
    }

    @GetMapping("/creditCard/{cardNum}")
    @ResponseBody
    public String getCreditCard(@PathVariable String cardNum) {
        CreditCard creditCard = creditCardRepository.findByCardNum(cardNum);
        return "user/viewCard";
    }

    @PutMapping("/editCreditCardDetails")
    @ResponseBody
    public void updateCreditCard(@RequestBody CreditCard creditCard) {
        creditCardRepository.updateCreditCardInfo(creditCard.getCardNum(), creditCard.getName(),
                creditCard.getSecurityCode(), creditCard.getExpiryDate());
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userCredentials", new User());
        return "user/register";
    }

    @RequestMapping(value = "/secureRegister", method = RequestMethod.POST)
    public String register(@ModelAttribute("userCredentials") User userCredentials, BindingResult bindingResult,
            Model model) {
        loginValidator.validate(userCredentials, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("currentUser", context.getAuthentication().getName());

        userService.saveExecUser(userCredentials);

        model.addAttribute("userCredentials", userCredentials);
        model.addAttribute("msg", "Successfully created user " + userCredentials.getEmail() + ".");

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "user/login";
    }

    @RequestMapping(value = "/secureLogin", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        boolean exists = securityService.login(email, password);

        if (exists) {
            model.addAttribute("msg", "Logged in successfully as " + userRepository.findByEmail(email).getEmail());
            model.addAttribute("userCredentials", userRepository.findByEmail(email));
            return "user/user";
        } else {
            model.addAttribute("msg", "User " + email + " does not exist");
            return "user/fail";
        }
    }

    @PreAuthorize("hasRole('EXEC')")
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Model model) {
        return "user/deleteAccount";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("email") String email, @RequestParam("password") String password,
            Model model) {
        User user = userService.findByEmail(email);

        if (user != null) {
            if (userService.deleteExecUser(user, password)) {
                model.addAttribute("msg",
                        "Successfully removed executive privileges from user " + user.getEmail() + ".");
                return "user/success";
            } else {
                model.addAttribute("msg", "Could not remove executive privileges for user" + user.getEmail()
                        + ". Password doesn't match");
                return "user/fail";
            }
        } else {
            model.addAttribute("msg", "User " + email + " does not exist.");
            return "user/fail";
        }
    }
}
