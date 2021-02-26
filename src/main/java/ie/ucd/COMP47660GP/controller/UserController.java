package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import ie.ucd.COMP47660GP.service.LoginService;
import ie.ucd.COMP47660GP.service.impl.LoginServiceImpl;
import ie.ucd.COMP47660GP.service.impl.UserService;
import ie.ucd.COMP47660GP.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

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

    int ref;   // Testing purposes

    // POST new executive club member
    // TODO: id params when creating URI
    @PostMapping(value="/registerMember", params = {"name", "surname", "address", "phone", "email"})
    public ResponseEntity<String> createReservation(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname,
                                                    @RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone,
                                                    @RequestParam(value = "email") String email) throws URISyntaxException {

//        userRepository.createMember(name, surname, address, phone, email);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/member/"+ref;  // Create new URI for reservation
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));

        return new ResponseEntity<>("TEST", headers, HttpStatus.CREATED);  // return info back to client class
    }

    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public User getUser(@PathVariable String id) {
//
//        return userRepository.findUser(id);
        return null;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userCredentials", new User());
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@ModelAttribute("userCredentials") User userCredentials, BindingResult bindingResult, Model model){
        loginValidator.validate(userCredentials, bindingResult);

        if (bindingResult.hasErrors()){
            return "register";
        }

        userService.saveExecUser(userCredentials);

        loginService.autoLogin(userCredentials.getEmail(), userCredentials.getPassword());

        model.addAttribute("userCredentials", userCredentials);

        return "success";

    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
}

