package ie.ucd.COMP47660GP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnauthorisedUserException extends RuntimeException {
    static final long serialVersionUID = -6516152229878843037L;

    public UnauthorisedUserException() {
    }

    public UnauthorisedUserException(int id) {
        super(String.format("Unauthorised access for user id: '%d'", id));
    }

}

