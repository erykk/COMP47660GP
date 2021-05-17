package ie.ucd.COMP47660GP.config;

import ie.ucd.COMP47660GP.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
//                .authorizeRequests()
//            .antMatchers("/register", "/", "/login", "/secureRegister", "/secureLogin", "/resources/**",
//                    "/images/**", "/reservation", "/mem/{id}", "/creditCard", "/creditCard/{cardNum}", "/editCreditCardDetails",
//                    "/registerCard", "/editPersonalDetails", "/flight", "/create-reservation", "/createMember",
//                    "/create-reservation/*", "/deleteAccount", "/success", "/fail")
//            .permitAll().anyRequest().authenticated().and()
//            .formLogin().loginPage("/login")
//            .loginProcessingUrl("/secureLogin").usernameParameter("username").passwordParameter("password").and()
//            .logout()
//                .logoutSuccessUrl("/logoutSuccess")
//                .invalidateHttpSession(true)
//                .deleteCookies("SESSID")
//                .permitAll().and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .sessionFixation().newSession()
//                .invalidSessionUrl("/login");
        http.authorizeRequests().
                antMatchers("/landing").permitAll().
                antMatchers("/register").permitAll().
                antMatchers("/secureRegister").permitAll().
                antMatchers("/login").permitAll().
                antMatchers("/secureLogin").permitAll().
                antMatchers("/flight").permitAll().
                antMatchers("/reservation").permitAll().
                antMatchers("/reservation/{id}").permitAll().

                antMatchers("/viewCards").authenticated().
                antMatchers("/viewCards").access("hasAuthority('EXEC')").
                antMatchers("/creditCard").authenticated().
                antMatchers("/creditCard").access("hasAuthority('EXEC')").
                antMatchers("/editCreditCardDetails/{id}").authenticated().
                antMatchers("/editCreditCardDetails/{id}").access("hasAuthority('EXEC')").

                antMatchers("/editPersonalDetails").authenticated().
                antMatchers("/reservationHistory/{id}").authenticated().
                antMatchers("/creditCard/{cardNum}").authenticated().






                and().formLogin().and().httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
