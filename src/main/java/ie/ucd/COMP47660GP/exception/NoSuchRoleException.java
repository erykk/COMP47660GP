package ie.ucd.COMP47660GP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// If there is no user listed with the given reference then throw this exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchRoleException extends RuntimeException {
    static final long serialVersionUID = -6516152229878843037L;

    public NoSuchRoleException() {
    }

    public NoSuchRoleException(String name) {
        super(String.format("Role not found for given user id: '%s'", name));
    }
}
