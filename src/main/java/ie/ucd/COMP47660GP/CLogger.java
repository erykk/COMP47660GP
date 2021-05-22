package ie.ucd.COMP47660GP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class CLogger {

    private CLogger(){}

    private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

    public static void info(String endpoint, String msg, SecurityContext context) { logger.info(endpoint + " -> " + msg + " [By : " + currentAuthUsername(context) + "]" ); }
    public static void warn(String endpoint, String msg, SecurityContext context) { logger.warn(endpoint + " -> " + msg + " [By : " + currentAuthUsername(context) + "]" ); }
    public static void error(String endpoint, String msg, SecurityContext context) { logger.error(endpoint + " -> " + msg + " [By : " + currentAuthUsername(context) + "]" ); }

    private static String currentAuthUsername(SecurityContext context) {
        if (context.getAuthentication() != null) {
            return context.getAuthentication().getName();
        }
        return "anonymous";

    }

}
