package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    UserRepository userRepository;

    // POST new executive club member
    @PostMapping("/createMember")
    public ResponseEntity addMember(@Valid @RequestBody User user) throws URISyntaxException  {
        System.out.println("TESTING MEMBER");
        userRepository.save(user);
        System.out.println("User id: "+user.getId());
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/member/"+user.getId();  // Create new URI for new member
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

}

