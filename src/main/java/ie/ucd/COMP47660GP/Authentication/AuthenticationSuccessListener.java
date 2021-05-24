package ie.ucd.COMP47660GP.Authentication;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import ie.ucd.COMP47660GP.service.LoginAttemptDenialService;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptDenialService loginAttemptDenialService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        logger.info("Authentication Success");
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginAttemptDenialService.loginSucceeded(request.getRemoteAddr());
        } else {
            loginAttemptDenialService.loginSucceeded(xfHeader.split(",")[0]);
        }
    }
}