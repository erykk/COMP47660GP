package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.service.LoginService;
import ie.ucd.COMP47660GP.authentication.LoginAttemptDenialService;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptDenialService loginAttemptDenialService;

    @Override
    public String currentLoggedInEmail() {
        Object userCredentials = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userCredentials instanceof UserDetails) {
            return ((UserDetails) userCredentials).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String email, String password) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
                    userDetails.getAuthorities());

            authenticationManager.authenticate(token);

            if (token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
                final String xfHeader = request.getHeader("X-Forwarded-For");
                if (xfHeader == null) {
                    loginAttemptDenialService.loginSucceeded(request.getRemoteAddr());
                } else {
                    loginAttemptDenialService.loginSucceeded(xfHeader.split(",")[0]);
                }
            }
        } catch (Exception e) {
            final String xfHeader = request.getHeader("X-Forwarded-For");
            if (xfHeader == null) {
                loginAttemptDenialService.loginFailed(request.getRemoteAddr());
            } else {
                loginAttemptDenialService.loginFailed(xfHeader.split(",")[0]);
            }
        }
    }

}
