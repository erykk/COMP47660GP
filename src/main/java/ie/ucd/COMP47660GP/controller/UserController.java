package ie.ucd.COMP47660GP.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import ie.ucd.COMP47660GP.CLogger;
import ie.ucd.COMP47660GP.entities.CreditCard;
import ie.ucd.COMP47660GP.entities.Role;
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
import org.springframework.validation.ObjectError;
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
    SecurityServiceImpl securityService;

    @Autowired
    CreditCardRepository creditCardRepository;

    // Assignment 3
    @Autowired
    UserValidator userValidator;
    @Autowired
    UserEditValidator userEditValidator;

    /**************************************
     *               START
     *           USER Requests
     **************************************/

//    @PreAuthorize("#username == authentication.name")
    @GetMapping("/user")
    public String user(@ModelAttribute("user2") User user2, BindingResult br, Model model) {

        securityService.checkLoggedInStatus(model);
        SecurityContext context = SecurityContextHolder.getContext();
        User currentUser = userRepository.findByUsername(context.getAuthentication().getName());

        user2.setPassword("");
        currentUser.setPassword("");

        model.addAttribute("user2", user2);
        model.addAttribute("user", currentUser);
        CLogger.info("/user", "access", SecurityContextHolder.getContext());

        return "user/user";
    }

    @PreAuthorize("#username == authentication.name  or hasAuthority('ADMIN')")
    @PostMapping(value = "/editPersonalDetails/{username}", consumes = "application/x-www-form-urlencoded")
    public String updatePersonaDetails(User user, Model model, BindingResult bindingResult, @PathVariable("username") String username) {

        securityService.checkLoggedInStatus(model);
        SecurityContext context = SecurityContextHolder.getContext();
        User currentUser = userRepository.findByUsername(context.getAuthentication().getName());

        if (userService.verifyUser(currentUser, user)){
            userEditValidator.validate(user,bindingResult);
            userRepository.updateUserId(user.getAddress(), user.getEmail(), user.getFirstName(), user.getLastName(),
                    user.getPhoneNum());
            model.addAttribute("msg", "User credentials changed successfully");
            CLogger.info("/editPersonalDetails", "successfully altered credentials for user" + username, SecurityContextHolder.getContext());
        } else {
            model.addAttribute("msg", "Invalid user credentials");
            CLogger.warn("/editPersonalDetails", "failed to alter credentials for user: " + username, SecurityContextHolder.getContext());
        }

        return "user/user";
    }

    //    @PreAuthorize("hasRole('EXEC')")
    @PreAuthorize("#username == authentication.name  or hasAuthority('ADMIN')")
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Model model) {
        securityService.checkLoggedInStatus(model);
        CLogger.info("/deleteAccount", "access", SecurityContextHolder.getContext());
        return "user/deleteAccount";
    }

    @PreAuthorize("#username == authentication.name  or hasAuthority('ADMIN')")
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("username") @NotNull String username, @RequestParam("password") String password, Model model) {
        securityService.checkLoggedInStatus(model);

        if (!userValidator.validDelete(username, password)){
            model.addAttribute("msg", "Invalid credentials");
            return "user/deleteAccount";
        }

        User user = userService.findByUsername(username);
        SecurityContext context = SecurityContextHolder.getContext();
        User user2 = userRepository.findByUsername(context.getAuthentication().getName());

        if(user.getId() != user2.getId()){
            CLogger.warn("/deleteAccount", "failed for username: " + username, SecurityContextHolder.getContext());
            throw new UnauthorisedUserException();
        }

        if (userService.deleteExecUser(user, password)) {
            model.addAttribute("msg","Successfully removed executive privileges from user " + user.getUsername() + ".");
            securityService.forceLogout(model);
            CLogger.info("/deleteAccount", "successful for username: " + username, SecurityContextHolder.getContext());
            return "user/success";
        } else {
            model.addAttribute("msg", "Could not remove executive privileges for user" + user.getUsername()
                    + ". Password doesn't match");
            CLogger.error("/deleteAccount", "failed for username, cant remove privileges for: " + username, SecurityContextHolder.getContext());
            return "user/fail";
        }
    }

    /**********************************
     *
     *        CREDIT CARD Requests
     **********************************/

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/registerCard/{username}")
    public String registerCard(Model model, @PathVariable("username") @NotNull String username) {
        CreditCard card = new CreditCard();
        User u = new User();
        u.setUsername(username);
        card.setUser(u);

        securityService.checkLoggedInStatus(model);

        model.addAttribute("cardCredentials", card);
        CLogger.info("/registerCard", "access", SecurityContextHolder.getContext());

        return "user/cardRegistration";
    }


    @PreAuthorize("#username == authentication.name")
    @RequestMapping(value = "/creditCard/{username}", method = RequestMethod.POST)
    public String addCreditCard(@ModelAttribute("cardCredentials") CreditCard cardCredentials,
                                BindingResult bindingResult, Model model, @PathVariable("username") String username) {
        securityService.checkLoggedInStatus(model);

        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        cardCredentials.setUser(user);
        cardValidator.validate(cardCredentials, bindingResult);
        if (bindingResult.hasErrors()) {
            CLogger.warn("/creditCard", "failed to add card for user: " + username, SecurityContextHolder.getContext());
            for (ObjectError error : bindingResult.getAllErrors()){
                CLogger.info("/creditCard", "add new - " + error.toString(), SecurityContextHolder.getContext());
            }
            return "user/cardRegistration";
        }

        cardService.save(cardCredentials);
        List<CreditCard> creditCards = creditCardRepository.findAllByUser(user);

        model.addAttribute("cardCredentials", cardCredentials);
        model.addAttribute("msg", "Successfully added card " + cardCredentials.getCardNum() + ".");
        model.addAttribute("creditCards", creditCards);
        model.addAttribute("user", user);
        CLogger.info("/creditCard", "added card for: " + username, SecurityContextHolder.getContext());

        return "user/viewCards";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/viewCards/{username}")
    public String cards(Model model, @PathVariable("username") String username) {
        securityService.checkLoggedInStatus(model);
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        List<CreditCard> creditCards = creditCardRepository.findAllByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("creditCards", creditCards);
        CLogger.info("/viewCards", "access", SecurityContextHolder.getContext());

        return "user/viewCards";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/editCreditCardDetails/{username}/{id}")
    public String editCreditCard(@PathVariable("username") String username,@PathVariable("id") int id, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        CreditCard card = creditCardRepository.findById(id).orElseThrow(() -> new NoSuchCreditCardException());

        if(user.getId() != card.getUser().getId()){
            CLogger.warn("/editCreditCardDetails", "attempted unauthorised access by user: " + user.getId() + " for card: " + card.toString(), SecurityContextHolder.getContext());
            throw new UnauthorisedUserException();
        }

        model.addAttribute("cardCredentials", card);
        CLogger.info("/editCreditCardDetails", "get card of id: " + id, SecurityContextHolder.getContext());

        return "user/editCard";
    }

    @PreAuthorize("#username == authentication.name")
    @PostMapping(value = "/editCreditCardDetails/{username}")
    public String updateCreditCard(CreditCard creditCard, @PathVariable("username") String username) {
        creditCardRepository.updateCreditCardInfo(creditCard.getCardNum(), creditCard.getName(),
                creditCard.getSecurityCode(), creditCard.getExpiryDate());

        CLogger.info("/editCreditCardDetails", "change card details for id: " + creditCard.toString(), SecurityContextHolder.getContext());
        return "redirect:/user";
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping("/editCreditCardDetails/{username}/{id}/delete")
    public String deleteCreditCard(@PathVariable("username") String username,@PathVariable("id") int id, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName());
        CreditCard card = creditCardRepository.findById(id).orElseThrow(() -> new NoSuchCreditCardException());

        if(user.getId() != card.getUser().getId()){
            CLogger.warn("/editCreditCardDetails", "attempted unauthorised access by user: " + user.getId() + " for card: " + card.toString(), SecurityContextHolder.getContext());
            throw new UnauthorisedUserException();
        }

        creditCardRepository.deleteById(card.getId());

        model.addAttribute("cardCredentials", card);
        CLogger.info("/editCreditCardDetails", "deleted card for id: " + id, SecurityContextHolder.getContext());

        return "user/viewCards";
    }

    /**************************************
     *
     *        REGISTRATION/LOGIN Requests
     **************************************/

    @GetMapping("getEmail/{email}")
    @ResponseBody
    public String checkIfEmailIsValid(@PathVariable String email) {
        User user = userRepository.findEmail(email);
        if (user == null) {
            CLogger.error("/getEmail", "for email: " + email, SecurityContextHolder.getContext());
            return "Invalid: Email already exists";
        }
        return "Valid: email does not exist";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userCredentials", new User());
        CLogger.info("/register", "access", SecurityContextHolder.getContext());
        return "user/register";
    }

    @RequestMapping(value = "/secureRegister", method = RequestMethod.POST)
    public String register(@ModelAttribute("userCredentials") User userCredentials, BindingResult bindingResult,
                           Model model) {
        userValidator.validate(userCredentials,bindingResult);

        if (bindingResult.hasErrors()) {
            CLogger.warn("/register", "failed to register new user ", SecurityContextHolder.getContext());
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
        CLogger.info("/register", "registered new user", SecurityContextHolder.getContext());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        securityService.checkLoggedInStatus(model);
        CLogger.info("/login", "access", SecurityContextHolder.getContext());

        return "user/login";
    }

//    @PreAuthorize("#username == authentication.name or hasAuthority('ADMIN')")
    @RequestMapping(value = "/secureLogin", method = RequestMethod.POST)
    public String login(@RequestParam("username") @NotNull String username, @RequestParam("password") String password, Model model) {
        securityService.checkLoggedInStatus(model);
        User user = userService.findByUsername(username);

        if (user == null){
            model.addAttribute("msg", "Invalid credentials");
            CLogger.warn("/login", "no user found for username: " + username, SecurityContextHolder.getContext());
            return "user/login";
        }

        CLogger.info("/secureLogin", "login called", SecurityContextHolder.getContext());

        if (!user.getExec()) {
            CLogger.warn("/login", "no executive account found for user: " + username, SecurityContextHolder.getContext());
            if (!hasAdminRole(user)){
                model.addAttribute("msg", "Invalid credentials");
                CLogger.warn("/login", "no executive or admin account found for user: " + username, SecurityContextHolder.getContext());
                return "user/login";
            }
        }

        try {
            securityService.autoLogin(username, password);
            securityService.checkLoggedInStatus(model);
            model.addAttribute("msg", "Logged in successfully as " + userRepository.findByUsername(username).getUsername());
            model.addAttribute("user", userRepository.findByUsername(username));
            CLogger.info("/login", "successful for username: " + username, SecurityContextHolder.getContext());

            return "user/user";

        } catch (NoSuchUserException e) {
            securityService.checkLoggedInStatus(model);
            model.addAttribute("msg", "Invalid credentials");
            CLogger.warn("/login", "failed for username: " + username, SecurityContextHolder.getContext());

            return "user/login";
        }

    }

    private static boolean hasAdminRole(User user){
        for (Role role : user.getRoles()){
            if(role.getName().contains("ADMIN"))
                return true;
        }
        return false;
    }
}
