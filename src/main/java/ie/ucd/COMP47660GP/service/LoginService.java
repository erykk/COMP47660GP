package ie.ucd.COMP47660GP.service;

public interface LoginService {
    String currentLoggedInEmail();

    void autoLogin(String email, String password);
}
