package ie.ucd.COMP47660GP.config;

import ie.ucd.COMP47660GP.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers("/register", "/", "/login", "/secureRegister", "/resources/**", "/images/**",
                "/mem/{id}", "/fail",  "/secureLogin").permitAll()

                .antMatchers("/editPersonalDetails/{username}", "/deleteAccount").authenticated()

                .antMatchers("/registerCard/{username}", "/creditCard/{username}", "/viewCards/{username}",
                "/editCreditCardDetails/{username}/{id}", "/editCreditCardDetails/{username}", "/editCreditCardDetails/{username}/{id}/delete").authenticated()

                .antMatchers("/create-reservation/{id}", "/create-reservation/{id}", "/create-reservation/{username}", "/reservation").permitAll()

                .antMatchers("/user/deleteReservation/{username}/{resID}", "/reservationHistory/{username}").authenticated()

                .antMatchers("/guestReservation", "/user/deleteGuestReservation/{resID}").permitAll()

                .antMatchers("/flight", "/flights").permitAll()

                .antMatchers("/admin", "/admin/reservation", "/editReservation/{id}", "/editReservation", "/admin/deleteReservation",
                        "/deleteReservation/{id}", "/addFlight", "/deleteFlight", "/findFlight", "/editFlight").access("hasAuthority('ADMIN')")

                .and()
            .formLogin().loginPage("/login")
            //    .loginProcessingUrl("/secureLogin").usernameParameter("username").passwordParameter("password").and()
            .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logoutSuccess")
                .invalidateHttpSession(true)
                .deleteCookies("SESSID")
                .permitAll().and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
                .invalidSessionUrl("/login");

//        .httpBasic()
////

//         .antMatchers(", "/reservation", "/mem/{id}", "/creditCard", "/creditCard/{cardNum}", "/editCreditCardDetails",
//                "/registerCard", "/editPersonalDetails", "/flight", "/flights", "/create-reservation", "/createMember",
//                "/create-reservation/*", "/deleteAccount", "/success", "/fail", "/guestReservation", "/user/deleteGuestReservation/*")
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
