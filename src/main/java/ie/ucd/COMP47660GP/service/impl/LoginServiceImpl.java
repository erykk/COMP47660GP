package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String currentLoggedInEmail(){
        Object userCredentials = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userCredentials instanceof UserDetails){
            return ((UserDetails) userCredentials).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String email, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        if (token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }

}
