package ie.ucd.COMP47660GP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        } catch (Exception ignored) {}

        state = token.isAuthenticated();

        if(state){
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        return state;
    }

    public void checkLoggedInStatus(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !authentication.getName().equals("anonymousUser")){
            model.addAttribute("logged_in", true);
        } else {
            model.addAttribute("logged_in", false);
        }
    }
    public void forceLogout (Model model) {
        SecurityContextHolder.clearContext();
        model.addAttribute("logged_in", false);
    }
}
