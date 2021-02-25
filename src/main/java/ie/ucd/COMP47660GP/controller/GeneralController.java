package ie.ucd.COMP47660GP.controller;

import ie.ucd.COMP47660GP.entities.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class GeneralController {

    @GetMapping("/")
    public String getLanding(Model model) {
        List<String> origins = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        origins.add("Dublin");
        destinations.add("London");
        destinations.add("Paris");
        destinations.add("Madrid");
        destinations.add("New York");

        model.addAttribute("origins", origins);
        model.addAttribute("destinations", destinations);

        Flight flight = new Flight();
        model.addAttribute("flight", flight);

        return "landing";
    }
}
