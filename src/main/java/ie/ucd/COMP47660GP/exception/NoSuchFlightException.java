package ie.ucd.COMP47660GP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// If there is no flight listed with the given reference after calling GET method (for single instance) then throw this exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchFlightException extends RuntimeException {
    static final long serialVersionUID = -6516152229878843037L;
}
