package ie.ucd.COMP47660GP;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = { "ie.ucd.COMP47660GP.service.impl", "ie.ucd.COMP47660GP.service",
        "ie.ucd.COMP47660GP.config", "ie.ucd.COMP47660GP.controller", "ie.ucd.COMP47660GP.validator" })
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
