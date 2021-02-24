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
    Random random = new Random();
    List<Integer> referenceNumbers = new ArrayList<>();

    // POST new executive club member
    @PostMapping("/executive-club-member")
    public ResponseEntity addMember(@Valid @RequestBody User user) throws URISyntaxException  {
        System.out.println("TESTING MEMBER");
        userRepository.save(user);
        int ref = 0;
//        do{
//            ref = random.nextInt(100000);
//        } while (referenceNumbers.contains(ref));

        referenceNumbers.add(ref);
        System.out.println("REF: "+ref);
        String path = ServletUriComponentsBuilder.fromCurrentContextPath().
                build().toUriString()+ "/member/"+ref++;  // Create new URI for this newly created profile
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(path));
        return new ResponseEntity(HttpStatus.CREATED);
    }



    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public User getUser(@PathVariable String id) {
//
//        return userRepository.findUser(id);
        return null;
    }

}

