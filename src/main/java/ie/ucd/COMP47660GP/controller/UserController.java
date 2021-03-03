package ie.ucd.COMP47660GP.controller;

import com.fasterxml.jackson.core.util.JsonParserSequence;
import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;

import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.LoginService;
import ie.ucd.COMP47660GP.service.impl.LoginServiceImpl;
import ie.ucd.COMP47660GP.service.impl.SecurityService;
import ie.ucd.COMP47660GP.service.impl.UserService;
import ie.ucd.COMP47660GP.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@Controller
public class UserController {

    @Autowired
    LoginValidator loginValidator;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Qualifier("loginServiceImpl")
    LoginService loginService;
    @Autowired
    SecurityService securityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    int ref; // Testing purposes

    /*

    // POST new executive club member
    @PostMapping("/createMember")
    public ResponseEntity addMember(@Valid @RequestBody User user) throws URISyntaxException {
        userRepository.save(user);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString() + "/user/" + user.getId();  // Create new URI for new member
    }


    // POST new executive club member
    // TODO: id params when creating URI
    @PostMapping(value = "/registerMember", params = { "name", "surname", "address", "phone", "email" })
    public ResponseEntity<String> createReservation(@RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname, @RequestParam(value = "address") String address,
            @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email)
            throws URISyntaxException {
        // userRepository.createMember(name, surname, address, phone, email);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/member/" + ref;
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        return new ResponseEntity(HttpStatus.CREATED);
    }



    @GetMapping("/member/{id}")
    @ResponseBody
    public User getMember(@PathVariable int id){
        User user = userRepository.findUser(id);
        return user;
    }

    @GetMapping("getEmail/{email}")
    @ResponseBody
    public String checkIfEmailIsValid(@PathVariable String email) {
        User user = userRepository.findEmail(email);
        if (user == null) {
            return "Invalid: Email already exists";
        }
        return "Valid: email does not exist";
    }

    @PutMapping("/editMemberPersonalInfo")
    @ResponseBody
    public void updatePersonalInfo(@RequestBody User updatedUser) {
        User user = userRepository.findUser(updatedUser.getId());
        int updated_id = user.getId();
        int current_id = updatedUser.getId();
        userRepository.delete(user);
        userRepository.save(updatedUser);
        userRepository.updateUserId(current_id,updated_id);  // does not work!!!

    }

    @DeleteMapping("/deleteMember/{id}")
    @ResponseBody
    public void deleteMember(@PathVariable int id){
//        User user = userRepository.findUser(id).orElseThrow(() -> new UserNotFoundException(id));
        User user = userRepository.findUser(id);
        userRepository.delete(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public User getUser(@PathVariable String id) {
        //
        // return userRepository.findUser(id);
        return null;
    }


     */

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userCredentials", new User());
        return "register";
    }

    @RequestMapping(value = "/secureRegister", method = RequestMethod.POST)
    public String register(@ModelAttribute("userCredentials") User userCredentials, BindingResult bindingResult,
            Model model) {
        loginValidator.validate(userCredentials, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("currentUser", context.getAuthentication().getName());

        userService.saveExecUser(userCredentials);

        // loginService.autoLogin(userCredentials.getEmail(),
        // userCredentials.getPassword());

        model.addAttribute("userCredentials", userCredentials);

        return "success";

    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/secureLogin", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        boolean exists = securityService.login(email, password);

        if (exists) {
            return "success";
        } else {
            model.addAttribute("msg", "login failed");
        }

        return "login";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Model model) {
        return "deleteAccount";
    }


    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userService.findByEmail(email);

        if (user != null){
            if(userService.deleteExecUser(user, password)) {
                model.addAttribute("msg", "Successfully removed executive privileges from user " + user.getEmail() + ".")
                return "success";
            } else {
                return "fail";
            }
        } else {
            return "null";
        }
    }
}
