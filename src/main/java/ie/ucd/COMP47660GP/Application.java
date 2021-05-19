package ie.ucd.COMP47660GP;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = { "ie.ucd.COMP47660GP.service.impl", "ie.ucd.COMP47660GP.service",
        "ie.ucd.COMP47660GP.config", "ie.ucd.COMP47660GP.controller", "ie.ucd.COMP47660GP.validator",
        "ie.ucd.COMP47660GP.authentication" })
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
