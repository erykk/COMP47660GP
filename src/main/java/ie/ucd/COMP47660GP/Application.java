package ie.ucd.COMP47660GP;

import ie.ucd.COMP47660GP.entities.Flight;
import ie.ucd.COMP47660GP.entities.Reservation;
import ie.ucd.COMP47660GP.entities.User;
import ie.ucd.COMP47660GP.repositories.FlightRepository;
import ie.ucd.COMP47660GP.repositories.ReservationRepository;
import ie.ucd.COMP47660GP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private FlightRepository flightRepository;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LocalDate localDate = LocalDate.of(2021,5,18);
        LocalTime localTime = LocalTime.of(10,15);
        LocalDateTime ldt = LocalDateTime.of(localDate,localTime);
        Flight flight = new Flight("Dublin","Paris",ldt,"GE5678");
        //flight.setId(111);

        User user = new User("test", "test", "test1", "test", "test");

        userRepository.save(user);
        flightRepository.save(flight);

        Reservation res = new Reservation(flight, user);
        reservationRepository.save(res);
    }

//        @Bean
//            public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//                return args-> {
//
//                    System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//                    String[] beanNames = ctx.getBeanDefinitionNames();
//                    Arrays.sort(beanNames);
//                    for (String beanName: beanNames) {
//                        System.out.println(beanName);
//                    }
//                };
//            }
}


