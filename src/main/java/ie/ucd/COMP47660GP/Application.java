package ie.ucd.COMP47660GP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"ie.ucd.COMP47660GP.service.impl","ie.ucd.COMP47660GP.service", "ie.ucd.COMP47660GP.config", "ie.ucd.COMP47660GP.controller", "ie.ucd.COMP47660GP.validator"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}


