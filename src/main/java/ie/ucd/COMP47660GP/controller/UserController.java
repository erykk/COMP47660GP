package ie.ucd.COMP47660GP.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchCreditCardException;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.LoginService;
import ie.ucd.COMP47660GP.service.impl.CreditCardService;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import ie.ucd.COMP47660GP.service.impl.UserService;
import ie.ucd.COMP47660GP.validator.CreditCardValidator;
import ie.ucd.COMP47660GP.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Controller
public class UserController {

    @Autowired
    LoginValidator loginValidator;
    @Autowired
    CreditCardValidator cardValidator;
    List<CreditCard> creditCards = new LinkedList<>();
    @Autowired
    UserService userService;
    @Autowired
    CreditCardService cardService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    @Qualifier("loginServiceImpl")
    LoginService loginService;
    @Autowired
    SecurityServiceImpl securityService;
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

    @PostMapping(value = "/editPersonalDetails", consumes = "application/x-www-form-urlencoded")
    public String updatePersonaDetails(User user) {
        userRepository.updateUserId(user.getAddress(), user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getPhoneNum());
        return "redirect:/user";
    }

    @GetMapping("/registerCard")
    public String registerCard(Model model) {
        model.addAttribute("cardCredentials", new CreditCard());
        securityService.checkLoggedInStatus(model);
        return "user/cardRegistration";
    }

    @RequestMapping(value = "/creditCard", method = RequestMethod.POST)
    public String addCreditCard(@ModelAttribute("cardCredentials") CreditCard cardCredentials,
            BindingResult bindingResult, Model model) {
        securityService.checkLoggedInStatus(model);
        cardValidator.validate(cardCredentials, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/cardRegistration";
        }

        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findEmail(context.getAuthentication().getName());
        cardCredentials.setUser(user);

        cardService.save(cardCredentials);

        model.addAttribute("cardCredentials", cardCredentials);
        model.addAttribute("msg", "Successfully added card " + cardCredentials.getCardNum() + ".");
        creditCards.add(cardCredentials);
        model.addAttribute("creditCards", creditCards);
        return "user/viewCards";
    }

    @GetMapping("/viewCards")
    public String cards(Model model) {
        securityService.checkLoggedInStatus(model);

        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findEmail(context.getAuthentication().getName());

        List<CreditCard> creditCards = creditCardRepository.findAllByUser(user);
        model.addAttribute("creditCards", creditCards);
        return "user/viewCards";
    }

    @GetMapping("/reservationHistory/{id}")
    public String history(@PathVariable("id") Long id, Model model) {
//        securityService.checkLoggedInStatus(model);
        List<Reservation> reservations = reservationRepository.findUsersReservations(id);
        model.addAttribute("reservations", reservations);
        return "user/reservationHistory";
    }

    @GetMapping("/creditCard/{cardNum}")
    public String getCreditCard(@PathVariable String cardNum, Model model) {
//        securityService.checkLoggedInStatus(model);
        CreditCard creditCard = creditCardRepository.findByCardNum(cardNum);
        model.addAttribute("creditcard", new CreditCard());
        return "user/viewCard";
    }

    @GetMapping("/editCreditCardDetails/{id}")
    public String editCreditCard(@PathVariable("id") int id, Model model) {
        CreditCard card = creditCardRepository.findById(id).orElseThrow(() -> new NoSuchCreditCardException());
        model.addAttribute("cardCredentials", card);

        return "user/editCard";
    }

    @PostMapping(value = "/editCreditCardDetails")
    public String updateCreditCard(CreditCard creditCard) {
        creditCardRepository.updateCreditCardInfo(creditCard.getCardNum(), creditCard.getName(),
                creditCard.getSecurityCode(), creditCard.getExpiryDate());
        return "redirect:/viewCards";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userCredentials", new User());
//        securityService.checkLoggedInStatus(model);
        return "user/register";
    }

    @RequestMapping(value = "/secureRegister", method = RequestMethod.POST)
    public String register(@ModelAttribute("userCredentials") User userCredentials, BindingResult bindingResult,
            Model model) {
//        securityService.checkLoggedInStatus(model);
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

    @GetMapping("/user")
    public String user(Model model) {
        securityService.checkLoggedInStatus(model);
        SecurityContext context = SecurityContextHolder.getContext();
        User currentUser = userRepository.findEmail(context.getAuthentication().getName());
        model.addAttribute("user", currentUser);
        return "user/user";
    }

    @GetMapping("/login")
    public String login(Model model) {
        securityService.checkLoggedInStatus(model);
        return "user/login";
    }

    @RequestMapping(value = "/secureLogin", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        securityService.checkLoggedInStatus(model);
        try {
            securityService.autoLogin(email, password);
            model.addAttribute("msg", "Logged in successfully as " + userRepository.findByEmail(email).getEmail());
            model.addAttribute("user", userRepository.findByEmail(email));
            return "user/user";
        } catch (NoSuchUserException e) {
            model.addAttribute("msg", "User " + email + " does not exist");
            return "user/fail";
        }
    }

    @PreAuthorize("hasRole('EXEC')")
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Model model) {
        securityService.checkLoggedInStatus(model);
        return "user/deleteAccount";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("email") String email, @RequestParam("password") String password,
            Model model) {
        securityService.checkLoggedInStatus(model);
        User user = userService.findByEmail(email);

        if (user != null) {
            if (userService.deleteExecUser(user, password)) {
                model.addAttribute("msg",
                        "Successfully removed executive privileges from user " + user.getEmail() + ".");
                securityService.forceLogout(model);
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
