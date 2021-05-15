package ie.ucd.COMP47660GP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

public final class CLogger {

    private CLogger(){}

    private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

    public static void info(String s) { logger.info(s); }
    public static void warn(String s) { logger.warn(s); }
    public static void error(String s) { logger.error(s); }

}
