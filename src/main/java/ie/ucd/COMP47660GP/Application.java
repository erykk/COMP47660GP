package ie.ucd.COMP47660GP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"ie.ucd.COMP47660GP.service.impl","ie.ucd.COMP47660GP.service", "ie.ucd.COMP47660GP.config", "ie.ucd.COMP47660GP.controller"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
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


