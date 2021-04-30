package ie.ucd.COMP47660GP.service.impl;

import org.springframework.ui.Model;

public interface SecurityService {
    String findLoggedInEmail();

    void checkLoggedInStatus(Model model);

    void autoLogin(String email, String password);

    void forceLogout (Model model);
}
