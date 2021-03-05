package ie.ucd.COMP47660GP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean login(String email, String password){
        boolean state = false;
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

        try {
            authenticationManager.authenticate(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        state = token.isAuthenticated();

        if(state){
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        return state;
    }
}
