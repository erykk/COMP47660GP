package ie.ucd.COMP47660GP.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    String currentLoggedInEmail();

    void autoLogin(String email, String password);
}
