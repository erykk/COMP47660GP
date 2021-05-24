package ie.ucd.COMP47660GP.Authentication;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import ie.ucd.COMP47660GP.service.LoginAttemptDenialService;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptDenialService loginAttemptDenialService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        logger.info("Authentication Failed");
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginAttemptDenialService.loginFailed(request.getRemoteAddr());
        } else {
            loginAttemptDenialService.loginFailed(xfHeader.split(",")[0]);
        }
    }
}
