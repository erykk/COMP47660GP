package ie.ucd.COMP47660GP.service.impl;

import ie.ucd.COMP47660GP.entities.User;

public interface UserService {

    void save(User user);
    User findByEmail(String email);
    void saveGuestUser(User user);
    void saveExecUser(User user);
    boolean deleteExecUser(User user, String password);

    // Assignment 3
    User findByUsername(String username);
    boolean verifyUser(User currentUser, User user);

}
