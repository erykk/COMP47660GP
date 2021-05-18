package ie.ucd.COMP47660GP.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ie.ucd.COMP47660GP.CLogger;
import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.exception.NoSuchCreditCardException;
import ie.ucd.COMP47660GP.exception.NoSuchUserException;
import ie.ucd.COMP47660GP.exception.UnauthorisedUserException;
import ie.ucd.COMP47660GP.repositories.CreditCardRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.LoginService;
import ie.ucd.COMP47660GP.service.impl.CreditCardService;
import ie.ucd.COMP47660GP.service.impl.SecurityServiceImpl;
import ie.ucd.COMP47660GP.service.impl.UserService;
import ie.ucd.COMP47660GP.validator.CreditCardValidator;
import ie.ucd.COMP47660GP.validator.LoginValidator;
import ie.ucd.COMP47660GP.validator.UserEditValidator;
import ie.ucd.COMP47660GP.validator.UserValidator;
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

import javax.validation.constraints.NotNull;
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

    // Assignment 3
    @Autowired
    UserValidator userValidator;
    @Autowired
    UserEditValidator userEditValidator;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAdminPage(){
        return  "admin";
    }

    @GetMapping("getEmail/{email}")
    @ResponseBody
    public String checkIfEmailIsValid(@PathVariable String email) {
        User user = userRepository.findEmail(email);
        if (user == null) {
            CLogger.error("Email already exists:" + email);
            return "Invalid: Email already exists";
        }
        return "Valid: email does not exist";
    }

    @PostMapping(value = "/editPersonalDetails", consumes = "application/x-www-form-urlencoded")
    public String updatePersonaDetails(User user, BindingResult bindingResult) {
//        System.out.println("/editPersonalDetail method");
//        SecurityContext context = SecurityContextHolder.getContext();
//        User user2 = userRepository.findByUsername(context.getAuthentication().getName());
//        if(user.getId() != user2.getId()){
////            throw new UnauthorisedUserException();
//            System.out.println(user.getId());
//            System.out.println(user2.getId());
//        }
        userEditValidator.validate(user,bindingResult);
        userRepository.updateUserId(user.getAddress(), user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getPhoneNum());
        CLogger.info("/editPersonalDetails, cancel: id: " + user.getId());
        return "user/user";
    }

    @GetMapping("/registerCard")
    public String registerCard(Model model) {
        model.addAttribute("cardCredentials", new CreditCard());
        securityService.checkLoggedInStatus(model);
        CLogger.info("/registerCard, new");
        return "user/cardRegistration";
    }

    @RequestMapping(value = "/creditCard", method = RequestMethod.POST)
    public String addCreditCard(@ModelAttribute("cardCredentials") CreditCard cardCredentials,
            BindingResult bindingResult, Model model) {
        securityService.checkLoggedInStatus(model);
        cardValidator.validate(cardCredentials, bindingResult);
        System.out.println("/creditCard TESTING");
        if (bindingResult.hasErrors()) {
            CLogger.error("/creditCard, failed to add card: id: " + cardCredentials.toString());
            return "user/cardRegistration";
        }

        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        System.out.println("/creditcard username: "+user.getUsername());
        cardCredentials.setUser(user);

        cardService.save(cardCredentials);

        model.addAttribute("cardCredentials", cardCredentials);
        model.addAttribute("msg", "Successfully added card " + cardCredentials.getCardNum() + ".");
        creditCards.add(cardCredentials);
        model.addAttribute("creditCards", creditCards);
        CLogger.info("/creditCard, add: id: " + cardCredentials.toString());
        return "user/viewCards";
    }

    @GetMapping("/viewCards")
    public String cards(Model model) {
        securityService.checkLoggedInStatus(model);

        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());

        List<CreditCard> creditCards = creditCardRepository.findAllByUser(user);
        model.addAttribute("creditCards", creditCards);
        CLogger.info("/viewCards, get");
        return "user/viewCards";
    }

    @GetMapping("/reservationHistory/{id}")
    public String history(@PathVariable("id") @NotNull Long id, Model model) {
//        securityService.checkLoggedInStatus(model);
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        if(user.getId() != id){
            CLogger.info("/editCreditCardDetails, attempted unauthorised reservation history access by user: " + id);
            throw new UnauthorisedUserException();
        }
        List<Reservation> reservations = reservationRepository.findUsersReservations(id);

        model.addAttribute("reservations", reservations);

        CLogger.info("/reservationHistory, id: " + id);
        return "user/reservationHistory";
    }

    @GetMapping("/creditCard/{cardNum}")
    public String getCreditCard(@PathVariable String cardNum, Model model) {
//        securityService.checkLoggedInStatus(model);
        CreditCard creditCard = creditCardRepository.findByCardNum(cardNum);
        model.addAttribute("creditcard", new CreditCard());
        CLogger.info("/creditCard, get: id: " + creditCard.toString());
        return "user/viewCard";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/editCreditCardDetails/{username}/{id}")
    public String editCreditCard(@PathVariable("username") String username,@PathVariable("id") int id, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        CreditCard card = creditCardRepository.findById(id).orElseThrow(() -> new NoSuchCreditCardException());
        if(user.getId() != card.getUser().getId()){
            CLogger.info("/editCreditCardDetails, attempted unauthorised access by user: " + user.getId() + " for card: " + card.toString());
            System.out.println("Unauthorised access");
            throw new UnauthorisedUserException();
        }
        model.addAttribute("cardCredentials", card);

        CLogger.info("/editCreditCardDetails, id: " + id);

        return "user/editCard";
    }

    @PostMapping(value = "/editCreditCardDetails")
    public String updateCreditCard(CreditCard creditCard) {

        creditCardRepository.updateCreditCardInfo(creditCard.getCardNum(), creditCard.getName(),
                creditCard.getSecurityCode(), creditCard.getExpiryDate());
        CLogger.info("/editCreditCardDetails, id: " + creditCard.toString());
        return "redirect:/viewCards";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userCredentials", new User());
//        securityService.checkLoggedInStatus(model);
        CLogger.info("/register, view");
        return "user/register";
    }

    @RequestMapping(value = "/secureRegister", method = RequestMethod.POST)
    public String register(@ModelAttribute("userCredentials") User userCredentials, BindingResult bindingResult,
            Model model) {
//        securityService.checkLoggedInStatus(model);
//        loginValidator.validate(userCredentials, bindingResult);
          userValidator.validate(userCredentials,bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("TESTING guest /secureRegister");
            CLogger.error("/register failed for id: " + userCredentials.getId());
            return "user/register";
        }

        if(!userCredentials.getUsername().equals("admin4145_")){
            SecurityContext context = SecurityContextHolder.getContext();
            model.addAttribute("currentUser", context.getAuthentication().getName());
        }

        if(userCredentials.getRole() == null){
            userCredentials.setRole("USER");
        }

        userService.saveExecUser(userCredentials);

        model.addAttribute("userCredentials", userCredentials);
        model.addAttribute("msg", "Successfully created user " + userCredentials.getUsername() + ".");

        CLogger.info("/register, id: " + userCredentials.getId());

        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        securityService.checkLoggedInStatus(model);
        SecurityContext context = SecurityContextHolder.getContext();
        User currentUser = userRepository.findByUsername(context.getAuthentication().getName());
        model.addAttribute("user", currentUser);
        CLogger.info("/user, id: " + currentUser.getId());
        return "user/user";
    }

    @GetMapping("/login")
    public String login(Model model) {
//        model.addAttribute("login", new Login());
        securityService.checkLoggedInStatus(model);
        CLogger.info("/login, view");
        return "user/login";
    }

    @RequestMapping(value = "/secureLogin", method = RequestMethod.POST)
    public String login(@RequestParam("username") @NotNull String username, @RequestParam("password") String password, Model model) {
        securityService.checkLoggedInStatus(model);
        try {
            securityService.autoLogin(username, password);
            model.addAttribute("msg", "Logged in successfully as " + userRepository.findByUsername(username).getUsername());
            model.addAttribute("user", userRepository.findByUsername(username));
            CLogger.info("/login successful for username: " + username);
            return "user/user";
        } catch (NoSuchUserException e) {
            model.addAttribute("msg", "User " + username + " does not exist");
            CLogger.error("/login failed for username: " + username);
            return "user/fail";
        }
    }

    @PreAuthorize("hasRole('EXEC')")
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Model model) {
        securityService.checkLoggedInStatus(model);
        CLogger.info("/deleteAccount, view");
        return "user/deleteAccount";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("username") @NotNull String username, @RequestParam("password") String password,
            Model model) {
        securityService.checkLoggedInStatus(model);
        if (!userValidator.validDelete(username, password)){
            model.addAttribute("msg", "Invalid credentials");
            return "user/deleteAccount";
        }
        User user = userService.findByUsername(username);
        SecurityContext context = SecurityContextHolder.getContext();
        User user2 = userRepository.findByUsername(context.getAuthentication().getName());
        if(user.getId() != user2.getId()){
            CLogger.error("/deleteAccount failed for username: " + username);
            throw new UnauthorisedUserException();
        }

        CLogger.info("/deleteAccount successful for username: " + username);

        if (user != null) {
            if (userService.deleteExecUser(user, password)) {
                model.addAttribute("msg",
                        "Successfully removed executive privileges from user " + user.getUsername() + ".");
                securityService.forceLogout(model);
                CLogger.info("/deleteAccount successful for username: " + username);
                return "user/success";
            } else {
                model.addAttribute("msg", "Could not remove executive privileges for user" + user.getUsername()
                        + ". Password doesn't match");
                CLogger.error("/deleteAccount failed for username: " + username);
                return "user/fail";
            }
        } else {
            model.addAttribute("msg", "User " + username + " does not exist.");
            CLogger.error("/deleteAccount failed for username: " + username);
            return "user/fail";
        }
    }


}
